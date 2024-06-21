package com.example.store.exception.payment;

import com.example.store.exception.StoreException;

public class PaymentApproveFail extends StoreException {
    private static String MESSAGE = "현재 진행중인 카카오페이 결제에 실패했습니다.";

    public PaymentApproveFail() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
