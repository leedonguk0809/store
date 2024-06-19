package com.example.store.exception.user;

import com.example.store.exception.StoreException;

public class AlreadyExistsEmail extends StoreException {
    private static String MESSAGE = "이미 사용하는 이메일 입니다.";

    public AlreadyExistsEmail() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
