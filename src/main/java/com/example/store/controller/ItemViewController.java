package com.example.store.controller;

import com.example.store.dto.ItemDTO;
import com.example.store.service.item.ItemService;
import kotlin.RequiresOptIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ItemViewController {
    private final ItemService itemService;

    @GetMapping("/item/detail/{itemId}")
    public String getItemDetail(@PathVariable Long itemId,
                                Model model) {
        ItemDTO item = itemService.getItem(itemId);
        model.addAttribute("item", item);
        return "item/itemDetail";
    }
}
