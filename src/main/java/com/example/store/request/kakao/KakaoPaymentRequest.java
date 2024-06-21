package com.example.store.request.kakao;

import com.example.store.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class KakaoPaymentRequest {
    private Long orderId;
    private Long userId;
    private String itemName;
    private int quantity;
    private int totalAmount;

    @Builder
    public KakaoPaymentRequest(Long orderId, Long userId, String itemName, int quantity, int totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }
}
