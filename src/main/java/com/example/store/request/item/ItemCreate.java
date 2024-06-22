package com.example.store.request.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCreate {
    private String name;
    private Integer price;
    private String info;
}