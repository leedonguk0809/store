package com.example.store.service.orders;

import com.example.store.domain.Orders;
import java.util.List;

public interface OrdersService {
    List<Orders> getAllOrders();
    Orders getOrderById(Long id);
    void createOrder(Orders order);
    void updateOrder(Long id, Orders order);
    void deleteOrder(Long id);
}
