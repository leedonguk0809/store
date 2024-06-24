package com.example.store.controller;

import com.example.store.domain.Order;
import com.example.store.domain.Payment;
import com.example.store.domain.User;
import com.example.store.exception.payment.PaymentNotFound;
import com.example.store.repository.mapper.PaymentMapper;
import com.example.store.request.order.AddressUpdate;
import com.example.store.request.order.OrderCreate;
import com.example.store.service.item.ItemService;
import com.example.store.service.order.OrderService;
import com.example.store.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    private final PaymentMapper paymentMapper;
    private final ItemService itemService;
    private final UserService userService;

    @PostMapping("/orders")
    @ResponseBody
    public ResponseEntity<Order> initOrder(@RequestBody OrderCreate orderCreate) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUsername(username);

        Long orderId = orderService.create(user.getId(), orderCreate);
        String orderName = orderService.updateTotalPrice(orderId);
        Order order = orderService.findByOrderId(orderId);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/orders/address")
    @ResponseBody
    public ResponseEntity<Object> updateAddress(@RequestBody AddressUpdate address) {
        orderService.updateAddress(address);
        return ResponseEntity.ok(address);
    }

    @PreAuthorize("hasRole('COMMON') or hasRole('ADMIN') " )
    @GetMapping("/orders/view")
    public String getOrderView(Long orderId, Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUsername(username);

        Order order = orderService.findByOrderId(orderId);
        String orderName = order.getOrderItems().get(0).getItem().getName() + "외";
        model.addAttribute("orderName",orderName);
        model.addAttribute("order", order);
        model.addAttribute("user",user);
        return "orders/orderView";
    }

    @PreAuthorize("hasRole('COMMON')")
    @GetMapping("/orders")
    public String getOrderList(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUsername(username);

        List<Order> orders = orderService.findByUserId(user.getId());
        model.addAttribute("orders",orders);
        return "orders/orderList";
    }

    @PreAuthorize("hasRole('COMMON')")
    @GetMapping("/orders/{orderId}")
    public String getOrderDetail(@PathVariable Long orderId, Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUsername(username);

        Order order = orderService.findByOrderId(orderId);
        Optional<Payment> payment = paymentMapper.findByTid(order.getTid());
        if(payment.isPresent()){
            model.addAttribute("payment",payment.get());
        }
        model.addAttribute("order",order);
        return "orders/orderDetail";
    }
}
