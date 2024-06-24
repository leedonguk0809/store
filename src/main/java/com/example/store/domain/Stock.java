package com.example.store.domain;

import lombok.Data;

@Data
public class Stock {
    private Long id;
    private Long itemId;
    private int quantity;
}
