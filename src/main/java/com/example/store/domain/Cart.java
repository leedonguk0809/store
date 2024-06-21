package com.example.store.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;


@Getter
public class Cart {
    private Long cartId;
    private Long userid;
    private Long totalPrice;
    private List<CartItem> cartItems;
    @Builder
    public Cart(Long cartId, Long userid, Long totalPrice) {
        this.cartId = cartId;
        this.userid = userid;
        this.totalPrice = totalPrice;
    }
}
