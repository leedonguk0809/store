package com.example.store.request.cart;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartEdit {
    private Long itemId;
    private Integer quantity;

    @Builder
    public CartEdit(Long itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
