package com.example.store.request.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemUpdate {
    private Integer price;
    private Integer quantity;
    private String info;
}
