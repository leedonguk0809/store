//package com.example.store.service;
//
//import com.example.store.domain.Item;
//import com.example.store.dto.ItemDTO;
//import com.example.store.exception.item.InvalidItemException;
//import com.example.store.exception.item.ItemNotFound;
//import com.example.store.repository.item.ItemRepository;
//import com.example.store.request.item.ItemCreate;
//import com.example.store.service.item.ItemService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//class ItemServiceTest {
//
//    @Mock
//    private ItemRepository itemRepository;
//
//    @InjectMocks
//    private ItemService itemService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void addItem_shouldAddItemSuccessfully() {
//        ItemCreate itemCreate = new ItemCreate();
//        itemCreate.setName("Test Item");
//        itemCreate.setPrice(100);
//        itemCreate.setInfo("Test Info");
//
//        itemService.addItem(itemCreate);
//
//        verify(itemRepository, times(1)).save(any(Item.class));
//    }
//
//    @Test
//    void addItem_shouldThrowInvalidItemException_WhenNameIsNull() {
//        ItemCreate itemCreate = new ItemCreate();
//        itemCreate.setName(null);
//        itemCreate.setPrice(100);
//        itemCreate.setInfo("Test Info");
//
//        InvalidItemException exception = assertThrows(InvalidItemException.class, () -> itemService.addItem(itemCreate));
//
//        assertThat(exception.getValidation()).containsEntry("이름은 필수 입력값입니다", "이름이 비어있습니다");
//    }
//
//    @Test
//    void addItem_shouldThrowInvalidItemException_WhenPriceIsInvalid() {
//        ItemCreate itemCreate = new ItemCreate();
//        itemCreate.setName("Test Item");
//        itemCreate.setPrice(0);
//        itemCreate.setInfo("Test Info");
//
//        InvalidItemException exception = assertThrows(InvalidItemException.class, () -> itemService.addItem(itemCreate));
//
//        assertThat(exception.getValidation()).containsEntry("가격", "가격은 0보다 커야합니다");
//    }
//
//    @Test
//    void getItem_shouldReturnItem_WhenItemExists() {
//        Item item = new Item();
//        item.setItemId(1L);
//        item.setName("Test Item");
//        item.setPrice(100);
//        item.setInfo("Test Info");
//
//        when(itemRepository.findById(anyLong())).thenReturn(item);
//
//        ItemDTO itemDTO = itemService.getItem(1L);
//
//        assertThat(itemDTO).isNotNull();
//        assertThat(itemDTO.getName()).isEqualTo("Test Item");
//    }
//
//    @Test
//    void getItem_shouldThrowItemNotFoundException_WhenItemDoesNotExist() {
//        when(itemRepository.findById(anyLong())).thenReturn(null);
//
//        ItemNotFound exception = assertThrows(ItemNotFound.class, () -> itemService.getItem(1L));
//
//        assertThat(exception.getMessage()).isEqualTo("지정된 ID의 항목을 찾을 수 없습니다. ID: 1");
//    }
//
//    @Test
//    void getAllItems_shouldReturnAllItems() {
//        Item item1 = new Item();
//        item1.setItemId(1L);
//        item1.setName("Test Item 1");
//        item1.setPrice(100);
//        item1.setInfo("Test Info 1");
//
//        Item item2 = new Item();
//        item2.setItemId(2L);
//        item2.setName("Test Item 2");
//        item2.setPrice(200);
//        item2.setInfo("Test Info 2");
//
//        when(itemRepository.findAll()).thenReturn(List.of(item1, item2));
//
//        List<ItemDTO> items = itemService.getAllItems();
//
//        assertThat(items).hasSize(2);
//    }
//
//    @Test
//    void updateItem_shouldUpdateItemSuccessfully() {
//        Item item = new Item();
//        item.setItemId(1L);
//        item.setName("Test Item");
//        item.setPrice(100);
//        item.setInfo("Test Info");
//
//        ItemCreate itemCreate = new ItemCreate();
//        itemCreate.setName("Updated Item");
//        itemCreate.setPrice(150);
//        itemCreate.setInfo("Updated Info");
//
//        when(itemRepository.findById(anyLong())).thenReturn(item);
//
//        itemService.updateItem(1L, itemCreate);
//
//        verify(itemRepository, times(1)).update(any(Item.class));
//    }
//
//    @Test
//    void updateItem_shouldThrowItemNotFoundException_WhenItemDoesNotExist() {
//        when(itemRepository.findById(anyLong())).thenReturn(null);
//
//        ItemCreate itemCreate = new ItemCreate();
//        itemCreate.setName("Updated Item");
//        itemCreate.setPrice(150);
//        itemCreate.setInfo("Updated Info");
//
//        ItemNotFound exception = assertThrows(ItemNotFound.class, () -> itemService.updateItem(1L, itemCreate));
//
//        assertThat(exception.getMessage()).isEqualTo("아이템 ID를 찾을 수 없습니다. ID: 1");
//    }
//
//    @Test
//    void deleteItem_shouldDeleteItemSuccessfully() {
//        Item item = new Item();
//        item.setItemId(1L);
//        item.setName("Test Item");
//        item.setPrice(100);
//        item.setInfo("Test Info");
//
//        when(itemRepository.findById(anyLong())).thenReturn(item);
//
//        itemService.deleteItem(1L);
//
//        verify(itemRepository, times(1)).delete(anyLong());
//    }
//
//    @Test
//    void deleteItem_shouldThrowItemNotFoundException_WhenItemDoesNotExist() {
//        when(itemRepository.findById(anyLong())).thenReturn(null);
//
//        ItemNotFound exception = assertThrows(ItemNotFound.class, () -> itemService.deleteItem(1L));
//
//        assertThat(exception.getMessage()).isEqualTo("아이템 ID를 찾을 수 없습니다. ID: 1");
//    }
//}
