package com.example.store.repository;

import com.example.store.domain.Item;
import com.example.store.repository.mapper.ItemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ItemRepositoryImpl.class)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @BeforeEach
    void setUp() {
        itemMapper.deleteAll();
    }

    @Test
    void saveAndFindItem() {
        Item item = Item.builder()
                .name("Test Item")
                .price(100L)
                .info("Test Info")
                .build();

        itemRepository.save(item);

        Item foundItem = itemRepository.findById(item.getItemId());
        assertThat(foundItem).isNotNull();
        assertThat(foundItem.getName()).isEqualTo("Test Item");
    }

    @Test
    void findAllItems() {
        Item item1 = Item.builder()
                .name("Test Item 1")
                .price(100L)
                .info("Test Info 1")
                .build();
        Item item2 = Item.builder()
                .name("Test Item 2")
                .price(200L)
                .info("Test Info 2")
                .build();

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();
        assertThat(items).hasSize(2);
    }

    @Test
    void updateItem() {
        Item item = Item.builder()
                .name("Test Item")
                .price(100L)
                .info("Test Info")
                .build();

        itemRepository.save(item);

        item.setName("Updated Item");
        item.setPrice(150L);
        item.setInfo("Updated Info");
        itemRepository.update(item);

        Item updatedItem = itemRepository.findById(item.getItemId());
        assertThat(updatedItem.getName()).isEqualTo("Updated Item");
        assertThat(updatedItem.getPrice()).isEqualTo(150L);
        assertThat(updatedItem.getInfo()).isEqualTo("Updated Info");
    }

    @Test
    void deleteItem() {
        Item item = Item.builder()
                .name("Test Item")
                .price(100L)
                .info("Test Info")
                .build();

        itemRepository.save(item);
        itemRepository.delete(item.getItemId());

        Item deletedItem = itemRepository.findById(item.getItemId());
        assertThat(deletedItem).isNull();
    }
}