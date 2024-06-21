package com.example.store.service;

import com.example.store.domain.Cart;
import com.example.store.domain.CartItem;
import com.example.store.domain.Item;
import com.example.store.exception.cart.CartNotFoundException;
import com.example.store.repository.mapper.CartItemMapper;
import com.example.store.repository.mapper.CartMapper;
import com.example.store.repository.mapper.ItemMapper;
import com.example.store.request.cart.CartEdit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    @Override
    public Cart findByUserId(Long userId) {
        return cartMapper.findByUserId(userId).orElseThrow(CartNotFoundException::new);
    }

    @Override
    public boolean existCart(Long userId) {
        Optional<Cart> cart = cartMapper.findByUserId(userId);
        return cart.isPresent();
    }

    @Transactional
    @Override
    public void addCart(Long userId,CartEdit cartEdit) {
        Cart cart = cartMapper.findByUserId(userId).orElseThrow(CartNotFoundException::new);
        Optional<CartItem> cartItem = cartItemMapper.findByCartIdAndItemId(cart.getId(), cartEdit.getItemId());

        if (cartItem.isPresent()){
            CartItem item = cartItem.get();
            int editQuantity = item.getItemCount()+ cartEdit.getQuantity();
            cartItemMapper.updateItemQuantity(item.getId(), editQuantity);
        }
        else{
            cartItemMapper.save(cart.getId(), cartEdit.getItemId(), cartEdit.getQuantity());
        }
    }

    @Transactional
    @Override
    public void editQuantity(Long userId,CartEdit cartEdit) {
        Cart cart = cartMapper.findByUserId(userId)
                .orElseThrow(CartNotFoundException::new);

        CartItem cartItem = cartItemMapper.findByCartIdAndItemId(cart.getId(), cartEdit.getItemId())
                .orElseThrow(CartNotFoundException::new);

        int editQuantity = Math.max(0,cartEdit.getQuantity());

        if (editQuantity == 0){
            this.deleteItem(cartItem.getCartId());
            return;
        }
        cartItemMapper.updateItemQuantity(cartItem.getId(), editQuantity);
    }

    @Transactional
    @Override
    public void deleteItem(Long cartItemId) {
        cartItemMapper.deleteById(cartItemId);
    }
}
