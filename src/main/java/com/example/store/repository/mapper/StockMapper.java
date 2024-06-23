package com.example.store.repository.mapper;

import com.example.store.domain.Stock;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface StockMapper {
    @Select("SELECT * FROM stock WHERE item_id = #{itemId} FOR UPDATE")
    Optional<Stock> findByItemIdForUpdate(Long itemId);

    @Insert("INSERT INTO stock(item_id,quantity) VALUES (#{itemId},#{quantity})")
    void initStock(@Param("itemId") Long itemId, @Param("quantity") int quantity);

    @Update("UPDATE stock SET quantity = quantity - #{quantity} WHERE item_id = #{itemId} AND quantity >= #{quantity}")
    int updateStock(@Param("itemId") Long itemId, @Param("quantity") int quantity);

    @Update("UPDATE stock SET quantity = quantity + #{quantity} WHERE item_id = #{itemId}")
    int addStock(@Param("itemId") Long itemId, @Param("quantity") int quantity);
}
