package com.example.store.domain;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Getter
public class CartItem {
    private Long id;
    private Long cartId;
    private Integer itemCount;
    private Item item;

    @Builder
    public CartItem(Long id, Long cartId, Integer itemCount) {
        this.id = id;
        this.cartId = cartId;
        this.itemCount = itemCount;
    }
}
