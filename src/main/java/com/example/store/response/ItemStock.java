package com.example.store.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemStock {
    private Long itemId;
    private String name;
    private Long price;
    private Long quantity;
    private String itemImage;

    @Builder
    public ItemStock(Long itemId, String name, Long price, Long quantity, String itemImage) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.itemImage = itemImage;
    }
}
