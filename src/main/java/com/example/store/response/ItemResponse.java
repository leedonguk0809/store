package com.example.store.response;

import com.example.store.domain.Item;
import com.example.store.repository.item.ItemRepository;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemResponse {
    private Long id;
    private String name;
    private Integer price;
    private String info;
    private String profileImage;

    @Builder
    public ItemResponse(Long id, String name, Integer price, String info, String profileImage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.info = info;
        this.profileImage = profileImage;
    }

    public static ItemResponse fromEntity(Item item){
        return ItemResponse.builder()
                .id(item.getItemId())
                .name(item.getName())
                .price(item.getPrice())
                .info(item.getInfo())
                .profileImage(item.getItemImage())
                .build();
    }
}
