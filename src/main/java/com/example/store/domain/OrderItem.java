package com.example.store.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long itemId;
    private Integer count;

    @Builder
    public OrderItem(Long orderItemId, Long orderId, Long itemId, Integer count) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.count = count;
    }
}
