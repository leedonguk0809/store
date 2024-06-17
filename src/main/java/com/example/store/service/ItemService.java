package com.example.store.service;

import com.example.store.domain.Item;
import com.example.store.dto.ItemDTO;
import com.example.store.repository.ItemRepository;
import com.example.store.request.ItemCreate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addItem(ItemCreate itemCreate) {
        Item item = Item.builder()
                .name(itemCreate.getName())
                .price(itemCreate.getPrice())
                .info(itemCreate.getInfo())
                .build();
        itemRepository.save(item);
    }

    public ItemDTO getItem(Long itemId) {
        Item item = itemRepository.findById(itemId);
        return new ItemDTO(item.getItemId(), item.getName(), item.getPrice(), item.getInfo());
    }

    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(item -> new ItemDTO(item.getItemId(), item.getName(), item.getPrice(), item.getInfo()))
                .collect(Collectors.toList());
    }

    public void updateItem(Long itemId, ItemCreate itemCreate) {
        Item item = Item.builder()
                .itemId(itemId)
                .name(itemCreate.getName())
                .price(itemCreate.getPrice())
                .info(itemCreate.getInfo())
                .build();
        itemRepository.update(item);
    }

    public void deleteItem(Long itemId) {
        itemRepository.delete(itemId);
    }
}