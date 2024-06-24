package com.example.store.service.order;

import com.example.store.domain.Item;
import com.example.store.domain.Order;
import com.example.store.repository.mapper.ItemMapper;
import com.example.store.repository.mapper.OrderItemsMapper;
import com.example.store.repository.mapper.OrderMapper;
import com.example.store.request.order.AddressUpdate;
import com.example.store.request.order.ItemCount;
import com.example.store.request.order.OrderCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final OrderItemsMapper orderItemsMapper;

    @Transactional
    public Long create(Long userId ,OrderCreate orderCreate){
        Order order = Order.builder()
                .userId(userId)
                .orderEnroll(LocalDateTime.now())
                .totalItemCount(orderCreate.getTotalCount())
                .orderStatus("INIT")
                .build();

        orderMapper.save(order);

        List<ItemCount> itemCountList = orderCreate.getItemCountList();
        for(ItemCount itemCount : itemCountList){
            Item item = itemMapper.findItemById(itemCount.getItemId());
            int totalPrice  = item.getPrice()*itemCount.getCount();
            orderItemsMapper.save(order.getId(), itemCount.getItemId(), itemCount.getCount(),totalPrice);
        }

        return order.getId();
    }

    public String updateTotalPrice(Long orderId){
        Order order = orderMapper.findById(orderId);
        order.calculateOrderPrice();
        orderMapper.updateTotalPrice(orderId,order.getOrderPrice());

        String orderName = order.getOrderItems().get(0).getItem().getName() + "....";
        return orderName;
    }

    public void updateAddress(AddressUpdate addressUpdate){
        orderMapper.updateAddress(addressUpdate.getOrderId(),addressUpdate.getZipcode()
                ,addressUpdate.getMainAddress(),addressUpdate.getDetailAddress());
    }

    public Order findByOrderId(Long orderId){
        Order order = orderMapper.findById(orderId);
        return order;
    }

    public List<Order> findByUserId(Long userId){
        return orderMapper.findByUserId(userId);
    }
}
