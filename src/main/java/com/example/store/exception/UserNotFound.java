package com.example.store.exception;


public class UserNotFound extends StoreException {
    private static String MESSAGE = "존재 하는 사용자가 없습니다.";

    public UserNotFound() {
        super(MESSAGE);
    }


    @Override
    public int getStatusCode() {
        return 404;
    }
}
