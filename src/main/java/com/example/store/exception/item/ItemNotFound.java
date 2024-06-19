package com.example.store.exception.item;

import com.example.store.exception.StoreException;

public class ItemNotFound extends StoreException {
    private static final String MESSAGE = "아이템을 찾을 수 없습니다.";

    public ItemNotFound(Long itemId) {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
