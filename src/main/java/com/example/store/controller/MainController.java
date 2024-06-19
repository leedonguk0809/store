package com.example.store.controller;

import com.example.store.dto.ItemDTO;
import com.example.store.request.item.ItemSearch;
import com.example.store.response.ItemResponse;
import com.example.store.response.Paging;
import com.example.store.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ItemService itemService;

    @GetMapping("/")
    public String main(Model model){
        ItemSearch itemSearch  = ItemSearch.builder()
                .page(1)
                .size(20)
                .asc(false).build();

        int totalPages = (int) Math.ceil((double) itemService.getAllItems().size() / itemSearch.getSize());
        Paging paging = new Paging(1,totalPages);

        List<ItemResponse> page = itemService.getList(itemSearch);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ANONYMOUS"))) {
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        model.addAttribute("paging",paging);
        model.addAttribute("itemSearch",itemSearch);
        model.addAttribute("Items",page);
        return "main";
    }
    @GetMapping("/item/paged")
    public String getPageV2(@RequestParam(name = "page") int page,
                            @RequestParam(name = "size") int size,
                            @RequestParam(name="keyword" ,defaultValue = "") String keyword,
                            Model model) {
        ItemSearch itemSearch = ItemSearch.builder()
                .page(page)
                .size(size)
                .keyword(keyword)
                .build();

        List<ItemResponse> pageList = itemService.getList(itemSearch);

        int totalPages = (int) Math.ceil((double) itemService.getAllItems().size() / itemSearch.getSize());
        Paging paging = new Paging(page,totalPages);

        model.addAttribute("paging",paging);
        model.addAttribute("itemSearch", itemSearch);
        model.addAttribute("Items", pageList);
        return "main";
    }

    @GetMapping("/item/{itemId}")
    public String getItemDetail(@PathVariable Long itemId,
                            Model model) {

        ItemDTO item = itemService.getItem(itemId);
        model.addAttribute("item", item);
        return "item/itemDetail";
    }
}
