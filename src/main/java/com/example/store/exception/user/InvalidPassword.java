package com.example.store.exception.user;

import com.example.store.exception.StoreException;

public class InvalidPassword extends StoreException {

    private static final String MESSAGE = "비밀번호가 올바르지 않습니다.";

    public InvalidPassword() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}

