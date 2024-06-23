package com.example.store.service.item;

import com.example.store.dto.ItemDTO;
import com.example.store.request.item.ItemCreate;
import com.example.store.request.item.ItemSearch;
import com.example.store.request.item.ItemUpdate;
import com.example.store.response.ItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    void addItem(ItemCreate itemCreate, MultipartFile itemImage);
    ItemDTO getItem(Long itemId);
    List<ItemDTO> getAllItems();
    List<ItemResponse> getList(ItemSearch itemSearch);
    void updateStock(Long itemId, ItemUpdate itemUpdate);
    void deleteItem(Long itemId);
    void addStock(Long itemId, int quantity); // 추가된 부분
    void updateStock(Long itemId, int quantity); // 추가된 부분
}
