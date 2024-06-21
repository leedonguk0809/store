package com.example.store.exception.payment;

import com.example.store.exception.StoreException;

public class PaymentNotFound extends StoreException {
    private static String MESSAGE = "존재 하는 결제 내역이 없습니다.";

    public PaymentNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
