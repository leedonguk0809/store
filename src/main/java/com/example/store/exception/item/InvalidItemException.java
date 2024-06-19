package com.example.store.exception.item;

import com.example.store.exception.StoreException;

public class InvalidItemException extends StoreException {
    public InvalidItemException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
