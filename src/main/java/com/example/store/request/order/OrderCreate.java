package com.example.store.request.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class OrderCreate {
    private int totalPrice;
    private int totalCount;

    @JsonProperty("itemCountList")
    private List<ItemCount> itemCountList;

    @Builder
    public OrderCreate(int totalPrice, int totalCount, List<ItemCount> itemCountList) {
        this.totalPrice = totalPrice;
        this.totalCount = totalCount;
        this.itemCountList = itemCountList;
    }
}
