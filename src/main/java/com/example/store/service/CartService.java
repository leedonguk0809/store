package com.example.store.service;

import com.example.store.domain.Cart;
import com.example.store.request.cart.CartEdit;

public interface CartService {
    Cart findByUserId(Long userId);
    boolean existCart(Long userId);
    void addCart(CartEdit cartEdit);
    void editQuantity(CartEdit cartEdit);
    void deleteItem(Long cartItemId);
}
