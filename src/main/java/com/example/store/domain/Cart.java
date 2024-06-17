package com.example.store.domain;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;


public class Cart {

    private Long cartId;
    private Long userId;
    private Long totalPrice;
}
