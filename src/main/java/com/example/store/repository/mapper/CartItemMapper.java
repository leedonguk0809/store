package com.example.store.repository.mapper;

import com.example.store.domain.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface CartItemMapper {
    Optional<CartItem> findByCartIdAndItemId(@Param("cartId") Long cartId, @Param("itemId") Long itemId);
    void updateItemQuantity(@Param("cartItemId") Long cartItemId,@Param("itemCount") int itemCount);
    void save(@Param("cartId") Long cartId,@Param("itemId") Long itemId, @Param("itemCount") int itemCount);
    void deleteById(@Param("cartItemId") Long cartItemId);
    void deleteByItemId(@Param("cartId") Long cartId, @Param("itemId") Long itemId);
    void deleteByCartId(@Param("cartId") Long cartId);
}
