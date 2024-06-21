package com.example.store.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
public class Cart {
    private Long id;
    private Long userId;
    private int totalPrice;
    private Set<CartItem> cartItems = new HashSet<>();
    @Builder
    public Cart(Long id, Long userId, int totalPrice) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }
}
