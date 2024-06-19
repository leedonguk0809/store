package com.example.store.service.item;

import com.example.store.dto.ItemDTO;
import com.example.store.request.item.ItemCreate;
import com.example.store.request.item.ItemSearch;
import com.example.store.response.ItemResponse;

import java.util.List;

public interface ItemService {
    void addItem(ItemCreate itemCreate);
    ItemDTO getItem(Long itemId);
    List<ItemDTO> getAllItems();
    List<ItemResponse> getList(ItemSearch itemSearch);
    void updateItem(Long itemId, ItemCreate itemCreate);
    void deleteItem(Long itemId);
}
