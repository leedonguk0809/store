package com.example.store.controller;

import com.example.store.dto.ItemDTO;
import com.example.store.request.item.ItemCreate;
import com.example.store.request.item.ItemUpdate;
import com.example.store.response.ApiResponse;
import com.example.store.service.item.ItemService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        List<ItemDTO> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "admin";
    }
    @PostMapping
    public ApiResponse<Void> addItem(
                                     @RequestParam("name") String name,
                                     @RequestParam("price") Integer price,
                                     @RequestParam("info") String info,
                                     @RequestParam("itemImage") MultipartFile itemImage) {
        ItemCreate itemCreate = new ItemCreate();
        itemCreate.setName(name);
        itemCreate.setPrice(price);
        itemCreate.setInfo(info);
        itemService.addItem(itemCreate, itemImage);
        return ApiResponse.of(true, "상품이 성공적으로 추가되었습니다.", null);
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
    public ApiResponse<Void> updateStock(@PathVariable Long itemId,
                                        @RequestBody ItemUpdate itemUpdate) {

        itemService.updateStock(itemId, itemUpdate);
        return ApiResponse.of(true, "상품이 성공적으로 수정되었습니다.", null);
    }

    @DeleteMapping("/{itemId}")
    public ApiResponse<Void> deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return ApiResponse.of(true, "상품이 성공적으로 삭제되었습니다.", null);
    }


}
