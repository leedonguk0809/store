package com.example.store.request.order;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemCount {
    private Long itemId;
    private Integer count;

    @Builder
    public ItemCount(Long itemId, Integer count) {
        this.itemId = itemId;
        this.count = count;
    }
}
