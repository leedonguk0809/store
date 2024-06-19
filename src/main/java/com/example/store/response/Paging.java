package com.example.store.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Paging {
    private int startPage = 1;
    private int endPage = 20;
    private int totalPage;

    public Paging(int page, int totalPage) {
        this.startPage = Math.max(1, page - 2);
        this.endPage = Math.min(totalPage,Math.min(totalPage,page) + 2);
        this.totalPage = totalPage;
    }
}
