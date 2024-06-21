package com.example.store.domain;

import com.example.store.response.kakao.PaymentInfo;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
public class Payment {
    private Long paymentId;
    private Long orderId;
    private Long userId;
    private String itemName;
    private String tid;
    private String aid;
    private Integer totalAmount;
    private Integer quantity;
    private Integer vat;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime approvedAt;
    @Builder
    public Payment(Long paymentId, Long orderId, Long userId, String itemName, String tid, String aid, Integer totalAmount, Integer quantity, Integer vat, String paymentMethod, LocalDateTime createdAt, LocalDateTime approvedAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.userId = userId;
        this.itemName = itemName;
        this.tid = tid;
        this.aid = aid;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
        this.vat = vat;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.approvedAt = approvedAt;
    }

    public static Payment fromResponse(PaymentInfo paymentInfo){
        return Payment.builder()
                .orderId(Long.valueOf(paymentInfo.getPartner_order_id()))
                .userId(Long.valueOf(paymentInfo.getPartner_user_id()))
                .itemName(paymentInfo.getItem_name())
                .tid(paymentInfo.getTid())
                .aid(paymentInfo.getAid())
                .totalAmount((int) paymentInfo.getAmount().getTotal())
                .quantity(paymentInfo.getQuantity())
                .vat((int) paymentInfo.getAmount().getVat())
                .paymentMethod(paymentInfo.getPayment_method_type())
                .createdAt(paymentInfo.getCreated_at())
                .approvedAt(paymentInfo.getApproved_at())
                .build();
    }
}
