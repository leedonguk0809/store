package com.example.store.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Cart {
    private Long cartId;
    private Long userId;
    private Long itemId;
    private Long totalPrice;

    @Builder
    public Cart(Long userId, Long itemId, Long totalPrice) {
        this.userId = userId;
        this.itemId = itemId;
        this.totalPrice = totalPrice;
    }


    public void updateTotalPrice(Long totalPrice){
        this.totalPrice = totalPrice;
    }

    public void updateItemId(Long itemId){
        this.itemId = itemId;
    }
}