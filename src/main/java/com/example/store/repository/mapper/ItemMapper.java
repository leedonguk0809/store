package com.example.store.repository.mapper;

import com.example.store.domain.Item;
import com.example.store.response.ItemStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    void insertItem(Item item);

    Item findItemById(@Param("itemId") Long itemId);

    List<Item> findAllItems();

    List<ItemStock> findAllWithStock();

    List<Item> findByPage(@Param("size") int size, @Param("offset") long offset,
                          @Param("asc") boolean asc, @Param("keyword") String keyword);
    void updateItem(Item item);

    void deleteItem(@Param("itemId") Long itemId);

    void deleteAll();
}