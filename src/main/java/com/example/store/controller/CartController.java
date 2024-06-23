package com.example.store.controller;

import com.example.store.common.config.UserPrincipal;
import com.example.store.domain.Cart;
import com.example.store.domain.CartItem;
import com.example.store.domain.User;
import com.example.store.exception.user.UserNotFound;
import com.example.store.request.cart.CartEdit;
import com.example.store.service.cart.CartService;
import com.example.store.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @GetMapping("/cart")
    public String cartList(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUsername(username);
        Cart cart = cartService.findByUserId(user.getId());
        List<CartItem> cartItems = cart.getCartItems();

        cartItems = cartItems.stream()
                .filter(cartItem -> cartItem.getItem() != null)
                .collect(Collectors.toList());
        if (!cartItems.isEmpty()){
            model.addAttribute("cartItems",cartItems);
        }
        model.addAttribute("cartId",cart.getId());
        return "cart/cartview";
    }

    @ResponseBody
    @PatchMapping("/cart/edit")
    public String cartItemEdit(@RequestBody CartEdit cartEdit,Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUsername(username);

        cartService.editQuantity(user.getId(),cartEdit);
        Cart cart = cartService.findByUserId(user.getId());
        model.addAttribute("cart",cart);
        return "redirect:/cart/cartView";
    }

    @ResponseBody
    @PostMapping("/cart/add")
    public ResponseEntity<Cart> addCartItem(@RequestBody CartEdit cartEdit){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Long userId = userPrincipal.getUserId();

            cartService.addCart(userId,cartEdit);
            Cart cart = cartService.findByUserId(userId);
            return ResponseEntity.ok(cart);
        }
        else{
            throw new UserNotFound();
        }
    }

    @DeleteMapping("/cart/{cartItemId}")
    @ResponseBody
    public ResponseEntity<Object> cartDelete(@PathVariable Long cartItemId, Model model){
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartService.deleteItem(cartItemId);
        return ResponseEntity.ok(null);
    }
}
