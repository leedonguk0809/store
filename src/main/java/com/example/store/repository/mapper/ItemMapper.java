package com.example.store.repository.mapper;

import com.example.store.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {


    void insertItem(Item item);

    Item findItemById(@Param("itemId") Long itemId);

    List<Item> findAllItems();

    void updateItem(Item item);

    void deleteItem(@Param("itemId") Long itemId);

    void deleteAll();
}