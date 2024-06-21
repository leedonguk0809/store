package com.example.store.service;

import com.example.store.domain.Orders;
import com.example.store.domain.Payment;
import com.example.store.exception.order.OrderNotFound;
import com.example.store.repository.mapper.OrdersMapper;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoPayService {
    private final PaymentRepository paymentRepository;
    private final OrdersMapper orderRepository;
    private final ObjectMapper objectMapper;

    @Value("${kakaopay.api.secret.key}")
    private String kakaopaySecretKey;

    @Value("${cid}")
    private String cid;

    @Value("${sample.host}")
    private String applicationHost;
    private static int TAX_FREE_AMOUNT = 0;

    @Transactional
    public ReadyResponse ready(KakaoPaymentRequest kakaoPaymentRequest) {
        // Request header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "DEV_SECRET_KEY " + kakaopaySecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

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

        orderRepository.updateOrderTid(kakaoPaymentRequest.getOrderId(), readyResponse.getTid());
        log.info("{} [KakaoPayment READY] {}", Date.from(Instant.now()),kakaoPaymentRequest.toString());

        return readyResponse;
    }

    @Transactional
    public Payment approve(String pgToken, Long orderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "SECRET_KEY " + kakaopaySecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.info("{} [KakaoPayment APPROVE INIT] {}", Date.from(Instant.now()),pgToken.toString());

        Orders orders = orderRepository.findById(orderId);

        ApproveRequest approveRequest = ApproveRequest.builder()
                .cid(cid)
                .tid(orders.getTid())
                .partnerOrderId(String.valueOf(orders.getOrderId()))
                .partnerUserId(String.valueOf(orders.getUserId()))
                .pgToken(pgToken)
                .build();

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

            log.info("{} [KakaoPayment APPROVE SUCCESS] {}", Date.from(Instant.now()),approveResponse);

            return payment;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
