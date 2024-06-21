package com.example.store.controller;

import com.example.store.domain.OrderItem;
import com.example.store.service.OrderItem.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// OrderItemController 클래스는 HTTP 요청을 처리하고 응답을 반환
@Controller
@RequestMapping("/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // 모든 OrderItem을 조회하여 목록 페이지를 반환하는 메서드
    @GetMapping
    public String listOrderItems(Model model) {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        model.addAttribute("orderItems", orderItems);
        return "order-items/list";
    }

    // ID로 특정 OrderItem을 조회하여 상세 페이지를 반환하는 메서드
    @GetMapping("/{id}")
    public String viewOrderItem(@PathVariable Long id, Model model) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        model.addAttribute("orderItem", orderItem);
        return "order-items/view";
    }

    // OrderItem 생성 폼을 보여주는 메서드
    @GetMapping("/create")
    public String showCreateOrderItemForm(Model model) {
        model.addAttribute("orderItem", new OrderItem());
        return "order-items/create";
    }

    // 새로운 OrderItem을 생성하는 메서드
    @PostMapping("/create")
    public String createOrderItem(@ModelAttribute OrderItem orderItem) {
        orderItemService.createOrderItem(orderItem);
        return "redirect:/order-items";
    }

    // OrderItem 수정 폼을 보여주는 메서드
    @GetMapping("/edit/{id}")
    public String showEditOrderItemForm(@PathVariable Long id, Model model) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        model.addAttribute("orderItem", orderItem);
        return "order-items/edit";
    }

    // 기존 OrderItem을 수정하는 메서드
    @PostMapping("/edit/{id}")
    public String updateOrderItem(@PathVariable Long id, @ModelAttribute OrderItem orderItem) {
        orderItemService.updateOrderItem(id, orderItem);
        return "redirect:/order-items";
    }

    // ID로 OrderItem을 삭제하는 메서드
    @PostMapping("/delete/{id}")
    public String deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return "redirect:/order-items";
    }

    // ID로 OrderItem의 수량을 업데이트하는 메서드
    @PostMapping("/update-quantity/{id}")
    public String updateOrderItemQuantity(@PathVariable Long id, @RequestParam int quantity) {
        orderItemService.updateQuantity(id, quantity);
        return "redirect:/order-items";
    }
}
