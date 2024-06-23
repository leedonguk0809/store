package com.example.store.domain;


import com.example.store.request.cart.CartEdit;
import lombok.Builder;
import lombok.Getter;


@Getter
public class CartItem {
    private Long id;
    private Long cartId;
    private int itemCount;
    private Item item;

    @Builder
    public CartItem(Long id, Long cartId, int itemCount) {
        this.id = id;
        this.cartId = cartId;
        this.itemCount = itemCount;
    }

    public int getTotalPrice(){
        if (item == null) {
            return 0;
        }
        return itemCount*item.getPrice();
    }
}
