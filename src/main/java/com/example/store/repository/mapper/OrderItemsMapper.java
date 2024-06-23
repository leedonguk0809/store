package com.example.store.repository.mapper;

import com.example.store.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderItemsMapper {

    Optional<OrderItem> findByOrderIdAndItemId(@Param("orderId") Long orderId, @Param("itemId") Long itemId);

    List<OrderItem> findByOrderId(@Param("orderId") Long orderId);
    void save(@Param("orderId") Long orderId, @Param("itemId") Long itemId, @Param("itemCount") int itemCount, @Param("totalPrice") int totalPrice);
    //내가 만든거
}
