package com.example.store.dto;

import com.example.store.domain.Item;
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
public class ItemDTO {
    private Long itemId;
    private String name;
    private Long price;
    private String info;
    private  String itemImage;

    public static ItemDTO fromEntity(Item item){
        return ItemDTO.builder()
                .itemId(item.getItemId())
                .name(item.getName())
                .price(item.getPrice())
                .info(item.getInfo())
                .itemImage(item.getItemImage())
                .build();
    }
}
