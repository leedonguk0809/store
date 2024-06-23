package com.example.store.request.cart;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemQuantity {
    private Long itemId;
    private Integer quantity;

    @Builder
    public ItemQuantity(Long itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
