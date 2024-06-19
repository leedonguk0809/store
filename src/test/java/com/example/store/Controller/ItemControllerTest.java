package com.example.store.Controller;

import com.example.store.controller.ItemController;
import com.example.store.dto.ItemDTO;
import com.example.store.exception.item.InvalidItemException;
import com.example.store.exception.item.ItemNotFound;

import com.example.store.request.item.ItemCreate;
import com.example.store.service.item.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    private ItemDTO itemDTO;

    @BeforeEach
    void setUp() {
        itemDTO = new ItemDTO(1L, "Test Item", 100L, "Test Info");
    }

    @Test
    void addItem_shouldAddItemSuccessfully() throws Exception {
        ItemCreate itemCreate = new ItemCreate();
        itemCreate.setName("Test Item");
        itemCreate.setPrice(100L);
        itemCreate.setInfo("Test Info");

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemCreate)))
                .andExpect(status().isOk());

        verify(itemService, times(1)).addItem(any(ItemCreate.class));
    }

    @Test
    void addItem_shouldThrowInvalidItemException_WhenNameIsNull() throws Exception {
        ItemCreate itemCreate = new ItemCreate();
        itemCreate.setName(null);
        itemCreate.setPrice(100L);
        itemCreate.setInfo("Test Info");

        InvalidItemException invalidItemException = new InvalidItemException("Invalid item data");
        invalidItemException.addValidation("name", "Item name cannot be null or empty");

        doThrow(invalidItemException).when(itemService).addItem(any(ItemCreate.class));

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemCreate)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid item data")))
                .andExpect(jsonPath("$.validation.name", is("Item name cannot be null or empty")));

        verify(itemService, times(1)).addItem(any(ItemCreate.class));
    }

    @Test
    void getItem_shouldReturnItem() throws Exception {
        when(itemService.getItem(anyLong())).thenReturn(itemDTO);

        mockMvc.perform(get("/items/{itemId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Item")))
                .andExpect(jsonPath("$.price", is(100)))
                .andExpect(jsonPath("$.info", is("Test Info")));

        verify(itemService, times(1)).getItem(1L);
    }

    @Test
    void getItem_shouldThrowItemNotFoundException_WhenItemDoesNotExist() throws Exception {
        when(itemService.getItem(anyLong())).thenThrow(new ItemNotFound(1L));

        mockMvc.perform(get("/items/{itemId}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Item with the specified ID was not found. ID: 1")));

        verify(itemService, times(1)).getItem(1L);
    }

    @Test
    void getAllItems_shouldReturnListOfItems() throws Exception {
        List<ItemDTO> items = Collections.singletonList(itemDTO);
        when(itemService.getAllItems()).thenReturn(items);

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Test Item")));

        verify(itemService, times(1)).getAllItems();
    }

    @Test
    void updateItem_shouldUpdateItemSuccessfully() throws Exception {
        ItemCreate itemCreate = new ItemCreate();
        itemCreate.setName("Updated Item");
        itemCreate.setPrice(150L);
        itemCreate.setInfo("Updated Info");

        mockMvc.perform(patch("/items/{itemId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemCreate)))
                .andExpect(status().isOk());

        verify(itemService, times(1)).updateItem(eq(1L), any(ItemCreate.class));
    }

    @Test
    void updateItem_shouldThrowItemNotFoundException_WhenItemDoesNotExist() throws Exception {
        ItemCreate itemCreate = new ItemCreate();
        itemCreate.setName("Updated Item");
        itemCreate.setPrice(150L);
        itemCreate.setInfo("Updated Info");

        doThrow(new ItemNotFound(1L)).when(itemService).updateItem(anyLong(), any(ItemCreate.class));

        mockMvc.perform(patch("/items/{itemId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemCreate)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Item with the specified ID was not found. ID: 1")));

        verify(itemService, times(1)).updateItem(eq(1L), any(ItemCreate.class));
    }

    @Test
    void deleteItem_shouldDeleteItemSuccessfully() throws Exception {
        mockMvc.perform(delete("/items/{itemId}", 1L))
                .andExpect(status().isOk());

        verify(itemService, times(1)).deleteItem(1L);
    }

    @Test
    void deleteItem_shouldThrowItemNotFoundException_WhenItemDoesNotExist() throws Exception {
        doThrow(new ItemNotFound(1L)).when(itemService).deleteItem(anyLong());

        mockMvc.perform(delete("/items/{itemId}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Item with the specified ID was not found. ID: 1")));

        verify(itemService, times(1)).deleteItem(1L);
    }
}
