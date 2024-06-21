package com.example.store.exception.payment;

import com.example.store.exception.StoreException;

public class ApproveResponseParsingFail extends StoreException {
    private static String MESSAGE = "결제 요청 응답을 파싱하는데 실패했습니다.";

    public ApproveResponseParsingFail() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
