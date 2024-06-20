package com.example.store.repository.mapper;

import com.example.store.domain.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrdersMapper {
    List<Orders> findAll();
    Orders findById(@Param("orderId") Long orderId);
    void insertOrder(Orders order);
    void insertOrderItem(@Param("orderId") Long orderId, @Param("itemId") Long itemId, @Param("itemCount") int itemCount, @Param("totalItemPrice") Long totalItemPrice);
    void updateOrder(Orders order);
    void deleteOrder(@Param("orderId") Long orderId);
}
