package com.example.store.repository;

import com.example.store.domain.Item;
import java.util.List;

public interface ItemRepository {
    void save(Item item);
    Item findById(Long itemId);
    List<Item> findAll();
    void update(Item item);
    void delete(Long itemId);
}
