package com.example.store.repository.item;

import com.example.store.domain.Item;
import com.example.store.repository.mapper.ItemMapper;
import com.example.store.response.ItemStock;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ItemStock> findAllWithStock() {
        return itemMapper.findAllWithStock();
    }

    @Override
    public List<Item> findByPage(int size, long offset, boolean asc, String keyword) {
        return itemMapper.findByPage(size,offset,asc,keyword);
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
