package com.example.store.service;

import com.example.store.domain.Item;
import com.example.store.repository.mapper.ItemMapper;
import com.example.store.request.item.ItemSearch;
import com.example.store.response.ItemResponse;
import com.example.store.service.item.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemServicePageTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper itemMapper;

    @BeforeEach
    public void clean(){
        itemMapper.deleteAll();
    }

    @Test
    @DisplayName("PostSearch를 통해서 게시물 페이지 조회가 가능하고 키워드가 없으면 전체 조회한다.")
    void test1() throws Exception {
        //given
        for (int i = 1; i <= 100; i++) {
            Item item = Item.builder()
                    .name("제목" + i)
                    .price(i*100)
                    .info("내용" + i)
                    .build();
            itemMapper.insertItem(item);
        }

        ItemSearch itemSearch = ItemSearch.builder()
                .page(1)
                .size(10)
                .asc(false).build();
        //when
        List<ItemResponse> results = itemService.getList(itemSearch);

        //then
        assertEquals(10,results.size());
        assertEquals("제목100",results.get(0).getName());
        assertEquals("제목91",results.get(9).getName());
    }

    @Test
    @DisplayName("PostSearch를 통해서 게시물 페이지 조회가 키워드가 이름 또는 상세 설명에 있는 게시물만 조회 가능하다.")
    void test2() throws Exception {
        //given
        for (int i = 1; i <= 100; i++) {
            Item item = Item.builder()
                    .name("제목" + i)
                    .price(i*100)
                    .info("내용" + i)
                    .build();
            itemMapper.insertItem(item);
        }

        ItemSearch itemSearch = ItemSearch.builder()
                .page(1)
                .size(10)
                .keyword("0")
                .asc(false).build();
        //when
        List<ItemResponse> results = itemService.getList(itemSearch);

        //then
        assertEquals(10,results.size());
        assertEquals("제목100",results.get(0).getName());
        assertEquals("제목10",results.get(9).getName());
    }
}
