package com.example.store.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Stock {
    private Long id;
    private Long itemId;
    private int quantity;

    @Builder
    public Stock(Long id, Long itemId, int quantity) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
