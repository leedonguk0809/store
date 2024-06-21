package com.example.store.service.orders;

import com.example.store.domain.Item;
import com.example.store.domain.Orders;
import com.example.store.repository.mapper.OrdersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersMapper ordersMapper;

    @Override
    public List<Orders> getAllOrders() {
        return ordersMapper.findAll();
    }

    @Override
    public List<Orders> getOrdersByUserId(Long userId) {
        return ordersMapper.findByUserId(userId);
    }

    @Override
    public Orders getOrderById(Long orderId) {
        return ordersMapper.findById(orderId);
    }

    @Override
    @Transactional
    public void createOrder(Orders order) {
        ordersMapper.insertOrder(order);
        for (Item item : order.getItems()) {
            ordersMapper.insertOrderItem(order.getOrderId(), item.getItemId(), 1, item.getPrice());
        }
    }

    @Override
    @Transactional
    public void updateOrder(Long orderId, Orders order) {
        order.setOrderId(orderId);
        ordersMapper.updateOrder(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        ordersMapper.deleteOrder(orderId);
    }
}
