package com.example.store.exception.cart;

import com.example.store.exception.StoreException;

public class CartNotFoundException extends StoreException {
    private static String MESSAGE = "사용자의 장바구니가 없습니다. ";

    public CartNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}