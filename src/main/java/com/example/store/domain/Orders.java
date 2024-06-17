package com.example.store.domain;

import lombok.Data;

import java.util.Date;



public class Orders {

    private Long orderId;
    private Long userId;
    private Long totalPrice;
    private Date orderEnroll;
    private String zipcode;
    private String mainAddress;
    private String detailAddress;
    private String orderStatus;
}
