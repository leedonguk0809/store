package com.example.store.controller;

import com.example.store.dto.ItemDTO;
import com.example.store.request.item.ItemAdd;
import com.example.store.service.item.ItemService;
import kotlin.RequiresOptIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(
            @RequestPart("data") ItemAdd itemAdd,
            @RequestPart("productImage") MultipartFile productImage) throws IOException {

        if (productImage.isEmpty()) {
            return ResponseEntity.badRequest().body("파일이 비어있습니다.");
        }

        // 실제 파일 저장 로직
        byte[] bytes = productImage.getBytes();
        String uploadDirectory = "src/main/resources/static/item/";
        // 파일 저장 경로와 파일명 설정
        String fileName = productImage.getOriginalFilename();
        Path path = Paths.get(uploadDirectory + fileName);
        Files.write(path, bytes);

        // 추가적인 처리 (예: 데이터베이스에 저장 등)

        return ResponseEntity.ok("파일 업로드 성공: " + fileName);
    }
}
