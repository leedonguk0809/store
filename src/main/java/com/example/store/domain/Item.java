package com.example.store.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    private Long itemId;
    private String name;
    private Integer price;
    private String info;
    private String itemImage;

    public Item(String name, Integer price, String info,String itemImage) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.itemImage = itemImage;
    }

}


