package com.example.store.controller;

import com.example.store.domain.Cart;
import com.example.store.domain.User;
import com.example.store.request.cart.CartEdit;
import com.example.store.service.CartService;
import com.example.store.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @GetMapping("/cart")
    public String cartList(@AuthenticationPrincipal UserDetails currentUser, Model model){
        User user = userService.getByUsername(currentUser.getUsername());
        Cart cart = cartService.findByUserId(user.getId());
        model.addAttribute("cart",cart);
        return "cart/cartview";
    }

    @ResponseBody
    @PostMapping("/cart/edit")
    public String cartItemEdit(@AuthenticationPrincipal UserDetails currentUser,@RequestBody CartEdit cartEdit,Model model){
        User user = userService.get(currentUser.getUsername(), currentUser.getPassword());
        cartService.editQuantity(user.getId(),cartEdit);
        Cart cart = cartService.findByUserId(user.getId());
        model.addAttribute("cart",cart);
        return "redirect:/cart/cartView";
        //응답 정상이면 응답 기반해서 다시 리로드
    }

    @ResponseBody
    @PostMapping("/cart/add")
    public ResponseEntity<Cart> addCartItem(@AuthenticationPrincipal UserDetails currentUser, @RequestBody CartEdit cartEdit){
        User user = userService.getByUsername(currentUser.getUsername());
        cartService.addCart(user.getId(),cartEdit);
        Cart cart = cartService.findByUserId(user.getId());
        return ResponseEntity.ok(cart);
        //응답이 정상이면 모달 창 뛰워서 현재 페이지에 있을지 갈지 여부 파악
    }

    @DeleteMapping("/cart/{cartItemId}")
    public String cartDelete( @AuthenticationPrincipal UserDetails currentUser,@PathVariable Long cartItemId,Model model){
        cartService.deleteItem(cartItemId);
        User user = userService.get(currentUser.getUsername(), currentUser.getPassword());
        Cart cart = cartService.findByUserId(user.getId());
        model.addAttribute("cart",cart);
        return "redirect:/cart/cartView";
    }
}
