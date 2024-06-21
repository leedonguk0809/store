package com.example.store.repository.mapper;

import com.example.store.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// OrderItemMapper 인터페이스는 OrderItemMapper.xml 파일의 SQL 매핑을 정의합니다.
@Mapper
public interface OrderItemMapper {

    void insertOrderItem(OrderItem orderItem);

    void updateOrderItem(OrderItem orderItem);

    void deleteOrderItem(@Param("orderItemId") Long orderItemId);

    OrderItem selectOrderItemById(@Param("orderItemId") Long orderItemId);

    List<OrderItem> selectAllOrderItems();

    void updateOrderItemQuantity(@Param("orderItemId") Long orderItemId, @Param("itemCount") int itemCount);
}
