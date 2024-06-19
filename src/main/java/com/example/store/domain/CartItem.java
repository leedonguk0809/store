package com.example.store.domain;


import lombok.Data;
import lombok.Getter;


@Getter
public class CartItem {
    private Long itemId;
    private Long cartId;

    private Integer itemCount;
}
