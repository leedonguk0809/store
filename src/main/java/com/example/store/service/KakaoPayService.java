package com.example.store.service;

import com.example.store.domain.*;
import com.example.store.exception.order.OrderNotFound;
import com.example.store.exception.payment.StockIsEmpty;
import com.example.store.repository.mapper.*;
import com.example.store.repository.payment.PaymentRepository;
import com.example.store.request.kakao.ApproveRequest;
import com.example.store.request.kakao.KakaoPaymentRequest;
import com.example.store.request.kakao.ReadyRequest;
import com.example.store.response.kakao.PaymentInfo;
import com.example.store.response.kakao.ReadyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoPayService {
    private final PaymentRepository paymentRepository;
    private final OrderMapper orderMapper;
    private final OrderItemsMapper orderItemsMapper;
    private final StockMapper stockMapper;
    private final CartMapper cartMapper;
    private final ObjectMapper objectMapper;

    @Value("${kakaopay.api.secret.key}")
    private String kakaopaySecretKey;

    @Value("${kakaopay.api.cid}")
    private String cid;

    @Value("${application.host}")
    private String applicationHost;
    private static int TAX_FREE_AMOUNT = 0;

    @Transactional
    public ReadyResponse ready(KakaoPaymentRequest kakaoPaymentRequest) {
        // Request header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "DEV_SECRET_KEY " + kakaopaySecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        orderMapper.updateOrderStatusById(kakaoPaymentRequest.getOrderId(),"READY");
        // Request param
        ReadyRequest readyRequest = ReadyRequest.builder()
                .cid(cid)
                .partnerOrderId(kakaoPaymentRequest.getOrderId().toString())
                .partnerUserId(kakaoPaymentRequest.getUserId().toString())
                .itemName(kakaoPaymentRequest.getItemName())
                .quantity(kakaoPaymentRequest.getQuantity())
                .totalAmount(kakaoPaymentRequest.getTotalAmount())
                .taxFreeAmount(TAX_FREE_AMOUNT)
                .approvalUrl(applicationHost+"/approve?partner_order_id="+kakaoPaymentRequest.getOrderId())
                .cancelUrl(applicationHost+"/cancel")
                .failUrl(applicationHost + "/fail")
                .build();

        // Send reqeust
        HttpEntity<ReadyRequest> entityMap = new HttpEntity<>(readyRequest, headers);
        ResponseEntity<ReadyResponse> response = new RestTemplate().postForEntity(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                entityMap,
                ReadyResponse.class
        );
        ReadyResponse readyResponse = response.getBody();

        orderMapper.updateTidById(kakaoPaymentRequest.getOrderId(), readyResponse.getTid());
        log.info("{} [KakaoPayment READY] {}", Date.from(Instant.now()),kakaoPaymentRequest.toString());

        return readyResponse;
    }

    @Transactional
    public Payment approve(String pgToken, Long orderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "SECRET_KEY " + kakaopaySecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.info("{} [KakaoPayment APPROVE INIT] {}", Date.from(Instant.now()),pgToken.toString());

        Order orders = orderMapper.findById(orderId);

        ApproveRequest approveRequest = ApproveRequest.builder()
                .cid(cid)
                .tid(orders.getTid())
                .partnerOrderId(String.valueOf(orders.getId()))
                .partnerUserId(String.valueOf(orders.getUserId()))
                .pgToken(pgToken)
                .build();

        List<OrderItem> orderItems = orderItemsMapper.findByOrderId(orders.getId());

        for (OrderItem orderItem : orderItems){
            Optional<Stock> stock = stockMapper.findByItemIdForUpdate(orderItem.getItem().getItemId());
            Integer orderCount = orderItem.getCount();

            if (stock.isEmpty() || stock.get().getQuantity() < orderCount) {
                throw new StockIsEmpty(orderItem.getItem().getName());
            }
        }

        for (OrderItem orderItem : orderItems) {
            int updatedRows = stockMapper.updateStock(orderItem.getItem().getItemId(), orderItem.getCount());
            if (updatedRows == 0) {
                // 재고가 부족하여 업데이트에 실패한 경우 트랜잭션 롤백
                throw new StockIsEmpty(orderItem.getItem().getName());
            }
        }

        // Send Request
        HttpEntity<ApproveRequest> entityMap = new HttpEntity<>(approveRequest, headers);
        try {
            ResponseEntity<String> response = new RestTemplate().postForEntity(
                    "https://open-api.kakaopay.com/online/v1/payment/approve",
                    entityMap,
                    String.class
            );

            //재고 수정

            String approveResponse = response.getBody();
            Payment payment = Payment.fromResponse(objectMapper.readValue(approveResponse, PaymentInfo.class));
            paymentRepository.save(payment);
            orderMapper.updateOrderStatusById(orderId,"APPROVE");

            List<Long> orderItemIdList = orders.getOrderItems().stream()
                    .map(orderItem -> orderItem.getItem().getItemId())
                    .collect(Collectors.toList());

            //cartMapper.deleteCartItemsByItemIds(orderItemIdList);

            log.info("{} [KakaoPayment APPROVE SUCCESS] {}", Date.from(Instant.now()),approveResponse);

            return payment;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
