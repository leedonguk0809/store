package com.example.store.request.cart;

import com.example.store.domain.Item;
import com.example.store.dto.ItemDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderView {
    private ItemDTO item;
    private Integer totalPrice;
    private Integer quantity;

    @Builder
    public OrderView(ItemDTO item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
        this.totalPrice = quantity * item.getPrice();
    }
}
