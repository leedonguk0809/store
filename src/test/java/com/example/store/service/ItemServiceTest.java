package com.example.store.service;

import com.example.store.domain.Item;
import com.example.store.dto.ItemDTO;
import com.example.store.repository.ItemRepository;
import com.example.store.request.ItemCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItem() {
        ItemCreate itemCreate = new ItemCreate();
        itemCreate.setName("Test Item");
        itemCreate.setPrice(100L);
        itemCreate.setInfo("Test Info");

        itemService.addItem(itemCreate);

        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void getItem() {
        Item item = Item.builder()
                .itemId(1L)
                .name("Test Item")
                .price(100L)
                .info("Test Info")
                .build();

        when(itemRepository.findById(1L)).thenReturn(item);

        ItemDTO itemDTO = itemService.getItem(1L);
        assertThat(itemDTO.getName()).isEqualTo("Test Item");
    }

    @Test
    void getAllItems() {
        Item item1 = Item.builder()
                .itemId(1L)
                .name("Test Item 1")
                .price(100L)
                .info("Test Info 1")
                .build();
        Item item2 = Item.builder()
                .itemId(2L)
                .name("Test Item 2")
                .price(200L)
                .info("Test Info 2")
                .build();

        when(itemRepository.findAll()).thenReturn(List.of(item1, item2));

        List<ItemDTO> items = itemService.getAllItems();
        assertThat(items).hasSize(2);
    }

    @Test
    void updateItem() {
        ItemCreate itemCreate = new ItemCreate();
        itemCreate.setName("Updated Item");
        itemCreate.setPrice(150L);
        itemCreate.setInfo("Updated Info");

        itemService.updateItem(1L, itemCreate);

        verify(itemRepository, times(1)).update(any(Item.class));
    }

    @Test
    void deleteItem() {
        itemService.deleteItem(1L);

        verify(itemRepository, times(1)).delete(1L);
    }
}