package com.example.store.service.orders;

import com.example.store.domain.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> getAllOrders();
    List<Orders> getOrdersByUserId(Long userId);
    Orders getOrderById(Long id);
    void createOrder(Orders order);
    void updateOrder(Long id, Orders order);
    void deleteOrder(Long id);
    void updateOrderStatus(Long id, String status);
    void updateOrderTid(Long id, String tid);
}
