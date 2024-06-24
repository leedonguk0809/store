package com.example.store.repository.mapper;

import com.example.store.domain.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Mapper
public interface CartMapper {
    Optional<Cart> findByUserId(@Param("userId") Long userId);
    Optional<Cart> findById(@Param("userId") Long userId);
    void save(@Param("cart") Cart cart);
    void deleteAll(@Param("userId") Long userId);
    int deleteCartItemsByItemIds(@Param("itemIds") List<Long> itemIds);
}
