package com.example.store.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Integer count;
    private Item item;

    @Builder
    public OrderItem(Long orderItemId, Long orderId, Integer count) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.count = count;
    }

    public Integer getOrderPrice(){
        return count*item.getPrice();
    }
}
