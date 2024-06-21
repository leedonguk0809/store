package com.example.store.exception.order;

import com.example.store.exception.StoreException;

public class OrderNotFound extends StoreException {
    private static String MESSAGE = "존재 하는 주문이 없습니다.";

    public OrderNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
