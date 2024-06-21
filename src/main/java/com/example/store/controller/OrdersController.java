package com.example.store.controller;

import com.example.store.domain.Item;
import com.example.store.domain.Orders;
import com.example.store.domain.User;
import com.example.store.service.orders.OrdersService;
import com.example.store.service.user.UserService;

import lombok.extern.log4j.Log4j2;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String buyNow(@RequestParam("itemId") Long itemId, @RequestParam("price") Long price, Model model, @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity) {
        try {
            // 현재 사용자 정보 가져오기
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.get(userDetails.getUsername(), userDetails.getPassword());

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

            // 로깅 및 JSON 생성
            JSONObject logDetails = new JSONObject();
            logDetails.put("userEmail", user.getEmail());
            logDetails.put("userId", user.getId());
            logDetails.put("orderId", order.getOrderId()); // Order 객체에서 주문 ID 가져오기

            if (quantity == 1) {
                // 단일 구매
                logDetails.put("itemId", itemId);
                logDetails.put("price", price);
                logDetails.put("orderPrice", order.getOrderPrice());
                logDetails.put("totalItemCount", 1); // 단일 구매이므로 1로 설정
                logDetails.put("itemName", "ItemName"); // 예시: 실제 아이템 이름으로 변경
            } else {
                // 다중 구매
                logDetails.put("totalItemCount", quantity);
                logDetails.put("totalPrice", price * quantity);
                logDetails.put("itemNames", "ItemName 외 " + (quantity - 1) + "개"); // 예시: 실제 아이템 이름으로 변경
            }

            log.info("User {} placed an order with ID {}. Order Details: {}", user.getEmail(), order.getOrderId(), logDetails.toString());

            // JSON 데이터를 파라미터로 전달
            model.addAttribute("orderDetails", logDetails.toString());

            // 연동필요: 결제 페이지로 리다이렉트 또는 다른 처리
            return "redirect:/orders/confirmation";
        } catch (JSONException e) {
            log.error("JSON 처리 중 오류 발생", e);
            return "error"; // 에러 페이지로 리다이렉트 또는 다른 처리
        }
        // 어딘지 모르겠는데 쿼리스트링이 있어야 할 것
        //
    }

    // 주문 생성 폼 보기
    @GetMapping("/create")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("order", new Orders());
        return "orders/create";
    }

    // 주문 생성
    @PostMapping("/create")
    public String createOrder(@ModelAttribute @Valid Orders order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "orders/create"; // 유효성 검사 실패 시 다시 입력 폼으로
        }

        // 현재 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.get(userDetails.getUsername(), userDetails.getPassword());

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
    public String updateOrder(@PathVariable("id") Long id, @ModelAttribute @Valid Orders order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "orders/edit"; // 유효성 검사 실패 시 다시 입력 폼으로
        }

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
