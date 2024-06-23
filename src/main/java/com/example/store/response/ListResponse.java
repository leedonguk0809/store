package com.example.store.response;

import java.util.List;


public class ListResponse<T> extends CommonResponse {
    List<T> dataList;

    public ListResponse(boolean success,int code,String message,List<T> dataList) {
        super(success,code,message);
        this.dataList = dataList;
    }
}
