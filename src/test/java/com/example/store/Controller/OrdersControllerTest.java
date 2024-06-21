package com.example.store.Controller;

import com.example.store.controller.OrdersController;
import com.example.store.domain.Orders;
import com.example.store.domain.User;
import com.example.store.service.orders.OrdersService;
import com.example.store.service.user.UserService;

import groovy.util.logging.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Log4j2
class OrdersControllerTest {

    @Mock
    private OrdersService ordersService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private OrdersController ordersController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ordersController).build();
    }

    @Test
    void testListOrders() throws Exception {
        List<Orders> ordersList = Arrays.asList(new Orders(), new Orders());
        when(ordersService.getAllOrders()).thenReturn(ordersList);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("orders"))
                .andExpect(view().name("orders/list"));

        verify(ordersService, times(1)).getAllOrders();
    }

    @Test
    void testViewOrder() throws Exception {
        Orders order = new Orders();
        when(ordersService.getOrderById(1L)).thenReturn(order);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("order"))
                .andExpect(view().name("orders/view"));

        verify(ordersService, times(1)).getOrderById(1L);
    }

    @Test
    void testShowCreateOrderForm() throws Exception {
        mockMvc.perform(get("/orders/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("order"))
                .andExpect(view().name("orders/create"));
    }

    @Test
    void testCreateOrder() throws Exception {
        User user = mock(User.class); // 모킹된 User 객체
        when(userService.get(any(String.class), any(String.class))).thenReturn(user);
        doNothing().when(ordersService).createOrder(any(Orders.class));

        mockMvc.perform(post("/orders/create")
                        .param("orderPrice", "1000")
                        .param("zipcode", "12345")
                        .param("mainAddress", "Main Address")
                        .param("detailAddress", "Detail Address"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders"));

        verify(ordersService, times(1)).createOrder(any(Orders.class));
    }

    @Test
    void testShowEditOrderForm() throws Exception {
        Orders order = new Orders();
        when(ordersService.getOrderById(1L)).thenReturn(order);

        mockMvc.perform(get("/orders/edit/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("order"))
                .andExpect(view().name("orders/edit"));

        verify(ordersService, times(1)).getOrderById(1L);
    }

    @Test
    void testUpdateOrder() throws Exception {
        doNothing().when(ordersService).updateOrder(anyLong(), any(Orders.class));

        mockMvc.perform(post("/orders/edit/1")
                        .param("orderPrice", "2000")
                        .param("zipcode", "12345")
                        .param("mainAddress", "Updated Main Address")
                        .param("detailAddress", "Updated Detail Address"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders"));

        verify(ordersService, times(1)).updateOrder(anyLong(), any(Orders.class));
    }

    @Test
    void testDeleteOrder() throws Exception {
        doNothing().when(ordersService).deleteOrder(anyLong());

        mockMvc.perform(post("/orders/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders"));

        verify(ordersService, times(1)).deleteOrder(anyLong());
    }

    @Test
    void testBuyNow() throws Exception {
        User user = mock(User.class); // 모킹된 User 객체
        when(user.getEmail()).thenReturn("test@example.com");
        when(user.getId()).thenReturn(1L);
        when(userService.get(any(String.class), any(String.class))).thenReturn(user);
        doNothing().when(ordersService).createOrder(any(Orders.class));

        mockMvc.perform(post("/orders/buy/now")
                        .param("itemId", "1")
                        .param("price", "1000")
                        .param("quantity", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders/confirmation"));

        verify(ordersService, times(1)).createOrder(any(Orders.class));
    }
}
