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
    private Long price;
    private String info;

public Item(String name, Long price, String info) {
        this.name = name;
        this.price = price;
        this.info = info;
    }

}


