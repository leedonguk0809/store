package com.example.store.request.item;

import lombok.Getter;

@Getter
public class ItemAdd {
    private String name;
    private int price;
    private int quantity;
    private String info;
}
