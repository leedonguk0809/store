package com.example.store.controller;

import com.example.store.dto.ItemDTO;
import com.example.store.request.ItemCreate;
import com.example.store.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public void addItem(@RequestBody ItemCreate itemCreate) {
        itemService.addItem(itemCreate);
    }

    @GetMapping("/{itemId}")
    public ItemDTO getItem(@PathVariable Long itemId) {
        return itemService.getItem(itemId);
    }

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @PatchMapping("/{itemId}")
    public void updateItem(@PathVariable Long itemId, @RequestBody ItemCreate itemCreate) {
        itemService.updateItem(itemId, itemCreate);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
    }
}