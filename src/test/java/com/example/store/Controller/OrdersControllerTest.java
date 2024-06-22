package com.example.store.Controller;

import com.example.store.controller.OrdersController;
import com.example.store.domain.Item;
import com.example.store.domain.Orders;
import com.example.store.domain.User;
import com.example.store.service.orders.OrdersService;
import com.example.store.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrdersControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrdersService ordersService;

    @Mock
    private UserService userService;

    @InjectMocks
    private OrdersController ordersController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ordersController).build();
    }

    @Test
    public void testBuyNow() throws Exception {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("user@example.com");

        User user = User.builder().id(1L).email("user@example.com").build();
        when(userService.getByUsername(anyString())).thenReturn(user);

        Item item = Item.builder().itemId(1L).price(100).build();

        mockMvc.perform(post("/orders/buy/now")
                        .param("itemId", "1")
                        .param("price", "100")
                        .param("quantity", "1")
                        .principal(principal))
                .andExpect(status().is3xxRedirection());

        verify(userService, times(1)).getByUsername("user@example.com");
        verify(ordersService, times(1)).createOrder(any(Orders.class));
    }
}
