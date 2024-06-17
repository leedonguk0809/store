package com.example.store.domain;

import lombok.Data;

import java.util.Date;


public class Payment {

    private Long paymentId;
    private Long orderId;
    private Long paymentPrice;
    private Date paymentDate;
    private String bank;


}
