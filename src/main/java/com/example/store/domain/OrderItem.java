package com.example.store.domain;


import lombok.Data;

@Data
public class OrderItem {

    private Long orderItemId;
    private Long orderId;
    private Long itemId;


}
