package com.example.store.controller;

import com.example.store.dto.ItemDTO;
import com.example.store.request.item.ItemAdd;
import com.example.store.request.item.ItemCreate;
import com.example.store.service.item.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
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