package com.example.store.request.cart;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartEdit {
    private Long userId;
    private Long itemId;
    private Integer quantity;

    @Builder
    public CartEdit(Long userId, Long itemId, Integer quantity) {
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
