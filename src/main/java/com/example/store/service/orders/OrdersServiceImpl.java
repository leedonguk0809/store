package com.example.store.service.orders;

import com.example.store.domain.Orders;
import com.example.store.repository.mapper.OrdersMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersMapper ordersMapper;

    public OrdersServiceImpl(OrdersMapper ordersMapper) {
        this.ordersMapper = ordersMapper;
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersMapper.findAll();
    }

    @Override
    public Orders getOrderById(Long id) {
        return ordersMapper.findById(id);
    }

    @Override
    public List<Orders> getOrdersByUserId(Long userId) {
        return ordersMapper.findByUserId(userId);
    }

    @Override
    @Transactional
    public void createOrder(Orders order) {
        ordersMapper.insertOrder(order);
        order.getItems().forEach(item -> {
            ordersMapper.insertOrderItem(order.getOrderId(), item.getItemId(), item.getItemCount(), item.getPrice(), item.getTotalItemPrice());
        });
    }

    @Override
    @Transactional
    public void updateOrder(Long id, Orders order) {
        order.setOrderId(id);
        ordersMapper.updateOrder(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        ordersMapper.deleteOrder(id);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long id, String status) {
        ordersMapper.updateOrderStatus(id, status);
    }

    @Override
    @Transactional
    public void updateOrderTid(Long id, String tid) {
        ordersMapper.updateOrderTid(id, tid);
    }
}
