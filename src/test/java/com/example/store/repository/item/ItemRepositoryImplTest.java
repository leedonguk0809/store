package com.example.store.repository.item;

import com.example.store.domain.Item;
import com.example.store.repository.mapper.ItemMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ItemRepositoryImplTest {
    @Autowired
    private ItemMapper mapper;

    @Autowired
    private ItemRepository itemRepository;
    
    @Test
    @DisplayName("사이즈, Offset, keyword 를 통해서 정한 크기와 오프셋에 맞는 아이템을 조회 가능하다.")
    void test1() throws Exception {
        //given
        for (int i = 1; i <= 100; i++) {
            Item item = Item.builder()
                    .name("제목" + i)
                    .price(i*100L)
                    .info("내용" + i)
                    .build();
            mapper.insertItem(item);
        }

        int page = 1;
        int size = 20;
        long offset =  (long)(Math.max(1,page)-1)*Math.min(size,100);
        String keyword = "";
        //when
        List<Item> result = itemRepository.findByPage(size, offset, false,keyword);
        //then
        assertEquals(result.size(),20);
        assertEquals("제목100",result.get(0).getName());
        assertEquals("제목81",result.get(19).getName());
    }

    @Test
    @DisplayName("키워드가 있는 경우는 키워드가 이름 또는 내용에 포함된 아이템만 조회 가능하다")
    void test2() throws Exception {
        //given
        for (int i = 1; i <= 100; i++) {
            Item item = Item.builder()
                    .name("제목" + i)
                    .price(i*100L)
                    .info("내용" + i)
                    .build();
            mapper.insertItem(item);
        }

        int page = 1;
        int size = 20;
        long offset =  (long)(Math.max(1,page)-1)*Math.min(size,100);
        String keyword = "0";
        //when
        List<Item> result = itemRepository.findByPage(size, offset, false,keyword);
        //then
        assertEquals(result.size(),10);
        assertEquals("제목100",result.get(0).getName());
        assertEquals("제목10",result.get(9).getName());
    }
}