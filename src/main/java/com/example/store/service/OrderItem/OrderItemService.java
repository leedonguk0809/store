package com.example.store.service.OrderItem;

import com.example.store.domain.OrderItem;
import com.example.store.repository.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// OrderItemService 클래스는 비즈니스 로직을 처리합니다.
@Service
public class OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    // 모든 OrderItem을 조회하는 메서드
    public List<OrderItem> getAllOrderItems() {
        return orderItemMapper.selectAllOrderItems();
    }

    // ID로 특정 OrderItem을 조회하는 메서드
    public OrderItem getOrderItemById(Long id) {
        return orderItemMapper.selectOrderItemById(id);
    }

    // 새로운 OrderItem을 생성하는 메서드
    public void createOrderItem(OrderItem orderItem) {
        orderItemMapper.insertOrderItem(orderItem);
    }

    // 기존 OrderItem을 수정하는 메서드
    public void updateOrderItem(Long id, OrderItem orderItem) {
        orderItem.setOrderItemId(id); // 업데이트할 OrderItem의 ID를 설정합니다.
        orderItemMapper.updateOrderItem(orderItem);
    }

    // ID로 OrderItem을 삭제하는 메서드
    public void deleteOrderItem(Long id) {
        orderItemMapper.deleteOrderItem(id);
    }

    // ID로 OrderItem의 수량을 업데이트하는 메서드
    public void updateQuantity(Long id, int quantity) {
        orderItemMapper.updateOrderItemQuantity(id, quantity);
    }
}
