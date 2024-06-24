package com.example.store.repository.item;

import com.example.store.domain.Item;
import com.example.store.response.ItemStock;

import java.util.List;

public interface ItemRepository {
    void save(Item item);
    Item findById(Long itemId);
    List<Item> findAll();
    List<ItemStock> findAllWithStock();
    List<Item> findByPage(int size, long offset, boolean asc, String keyword);
    void update(Item item);
    void delete(Long itemId);
}
