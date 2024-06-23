package com.example.store.exception.payment;

import com.example.store.domain.Item;
import com.example.store.exception.StoreException;

public class StockIsEmpty extends StoreException {
    private static String MESSAGE = "주문 상품의 재고가 부족하여 결제가 실패하였습니다";
    private  String emptyItem;
    public StockIsEmpty(String emptyItem) {
        super(MESSAGE);
        this.emptyItem=emptyItem;
    }
    @Override
    public int getStatusCode() {
        return 500;
    }

    public String getEmptyItem(){
        return this.emptyItem;
    }
}
