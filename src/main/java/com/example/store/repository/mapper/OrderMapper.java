package com.example.store.repository.mapper;

import com.example.store.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    //내가 만든거
    List<Order> findByUserId(@Param("userId") Long userId);
    Order findById(@Param("orderId") Long orderId);
    void save(@Param("order") Order order);
    void updateOrderStatusById(@Param("orderId") Long orderId, @Param("orderStatus") String orderStatus);

    void updateAddress(@Param("orderId") Long orderId, @Param("zipcode") String zipcode
            ,@Param("mainAddress") String mainAddress,@Param("detailAddress")String detailAddress);
    void updateTidById(@Param("orderId") Long orderId, @Param("tid") String tid);

    void updateTotalPrice(@Param("orderId") Long orderId, @Param("totalPrice") Integer totalPrice);
}
