package com.example.store.service.item;

import com.example.store.domain.Item;
import com.example.store.dto.ItemDTO;
import com.example.store.exception.item.InvalidItemException;
import com.example.store.exception.item.ItemNotFound;
import com.example.store.repository.item.ItemRepository;
import com.example.store.request.item.ItemCreate;
import com.example.store.request.item.ItemSearch;
import com.example.store.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public void addItem(ItemCreate itemCreate) {
        validateItemCreate(itemCreate);
        Item item = Item.builder()
                .name(itemCreate.getName())
                .price(itemCreate.getPrice())
                .info(itemCreate.getInfo())
                .build();
        itemRepository.save(item);
    }

    public ItemDTO getItem(Long itemId) {
        Item item = itemRepository.findById(itemId);
        if (item == null) {
            throw new ItemNotFound(itemId);
        }
        return ItemDTO.fromEntity(item);
    }

    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(ItemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemResponse> getList(ItemSearch itemSearch) {
        List<Item> page = itemRepository.findByPage(itemSearch.getSize(), itemSearch.getOffset(), itemSearch.isAsc(), itemSearch.getKeyword());
        return page.stream().map(ItemResponse::fromEntity).collect(Collectors.toList());
    }

    public void updateItem(Long itemId, ItemCreate itemCreate) {
        Item item = itemRepository.findById(itemId);
        if (item == null) {
            throw new ItemNotFound(itemId);
        }
        validateItemCreate(itemCreate);
        item.setName(itemCreate.getName());
        item.setPrice(itemCreate.getPrice());
        item.setInfo(itemCreate.getInfo());
        itemRepository.update(item);
    }

    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId);
        if (item == null) {
            throw new ItemNotFound(itemId);
        }
        itemRepository.delete(itemId);
    }

    private void validateItemCreate(ItemCreate itemCreate) {
        if (itemCreate.getName() == null || itemCreate.getName().isEmpty()) {
            InvalidItemException e = new InvalidItemException("아이템 데이터가 올바르지 않습니다.");
            e.addValidation("name", "이름이 비어있습니다.");
            throw e;
        }
        if (itemCreate.getPrice() == null || itemCreate.getPrice() <= 0) {
            InvalidItemException e = new InvalidItemException("아이템 데이터가 올바르지 않습니다.");
            e.addValidation("price", "가격이 0원보다 커야합니다.");
            throw e;
        }
    }
}