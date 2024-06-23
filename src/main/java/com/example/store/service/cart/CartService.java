package com.example.store.service.cart;

import com.example.store.domain.Cart;
import com.example.store.request.cart.CartEdit;

public interface CartService {
    Cart findByUserId(Long userId);
    boolean existCart(Long userId);
    void addCart(Long userId,CartEdit cartEdit);
    void editQuantity(Long userId,CartEdit cartEdit);
    void deleteItem(Long cartItemId);
}
