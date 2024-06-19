package com.example.store.repository.item;

import com.example.store.domain.Item;
import java.util.List;

public interface ItemRepository {
    void save(Item item);
    Item findById(Long itemId);
    List<Item> findAll();
    List<Item> findByPage(int size, long offset, boolean asc, String keyword);
    void update(Item item);
    void delete(Long itemId);
}
