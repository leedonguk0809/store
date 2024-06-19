package com.example.store.repository;

import com.example.store.domain.Item;
import com.example.store.repository.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class ItemRepositoryImpl implements ItemRepository {

    private final ItemMapper itemMapper;

    public ItemRepositoryImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public void save(Item item) {
        itemMapper.insertItem(item);
    }

    @Override
    public Item findById(Long itemId) {
        return itemMapper.findItemById(itemId);
    }

    @Override
    public List<Item> findAll() {
        return itemMapper.findAllItems();
    }

    @Override
    public void update(Item item) {
        itemMapper.updateItem(item);
    }

    @Override
    public void delete(Long itemId) {
        itemMapper.deleteItem(itemId);
    }
}
