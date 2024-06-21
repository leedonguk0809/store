package com.example.store.controller;

import com.example.store.domain.Item;
import com.example.store.domain.Orders;
import com.example.store.domain.User;
import com.example.store.service.orders.OrdersService;
import com.example.store.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    // 주문 목록 보기
    @GetMapping
    public String listOrders(Model model) {
        List<Orders> orders = ordersService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders/list";
    }

    // 주문 상세 보기
    @GetMapping("/{id}")
    public String viewOrder(@PathVariable("id") Long id, Model model) {
        Orders order = ordersService.getOrderById(id);
        model.addAttribute("order", order);
        return "orders/view";
    }

    // 바로구매 버튼 클릭 시 처리
    @PostMapping("/buy/now")

    public String buyNow(@RequestParam("itemId") Long itemId, @RequestParam("price") Integer price, Model model, @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity, Principal principal) {

        // 현재 사용자 정보 가져오기
        String email = principal.getName();
        User user = userService.getByUsername(email);

        // 아이템 생성
        Item item = Item.builder()
                .itemId(itemId)
                .price(price)
                .build();

        // 주문 생성
        Orders order = Orders.builder()
                .userId(user.getId())
                .orderPrice(price * quantity)
                .totalItemCount(quantity)
                .build();

        ordersService.createOrder(order);

        // 연동필요: 결제 페이지로 리다이렉트 또는 다른 처리
        return "redirect:/payment";
    }

    // 주문 생성 폼 보기
    @GetMapping("/create")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("order", new Orders());
        return "orders/create";
    }

    // 주문 생성
    @PostMapping("/create")
    public String createOrder(@ModelAttribute Orders order) {
        // 현재 사용자 정보 가져오기
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUsername(username);

        order.setUserId(user.getId());
        ordersService.createOrder(order);
        return "redirect:/orders"; // 유효성 검사 통과 시 주문 생성 후 주문 목록 페이지로 리다이렉트
    }

    // 주문 수정 폼 보기
    @GetMapping("/edit/{id}")
    public String showEditOrderForm(@PathVariable("id") Long id, Model model) {
        Orders order = ordersService.getOrderById(id);
        model.addAttribute("order", order);
        return "orders/edit";
    }

    // 주문 수정
    @PostMapping("/edit/{id}")
    public String updateOrder(@PathVariable("id") Long id, @ModelAttribute Orders order) {
        ordersService.updateOrder(id, order);
        return "redirect:/orders"; // 유효성 검사 통과 시 주문 수정 후 주문 목록 페이지로 리다이렉트
    }

    // 주문 삭제
    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        ordersService.deleteOrder(id);
        return "redirect:/orders"; // 주문 삭제 후 주문 목록 페이지로 리다이렉트
    }
}
