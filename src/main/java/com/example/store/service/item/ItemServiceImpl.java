package com.example.store.service.item;

import com.example.store.domain.Item;
import com.example.store.dto.ItemDTO;
import com.example.store.exception.item.InvalidItemException;
import com.example.store.exception.item.ItemNotFound;
import com.example.store.repository.item.ItemRepository;
import com.example.store.repository.mapper.StockMapper;
import com.example.store.request.item.ItemCreate;
import com.example.store.request.item.ItemSearch;
import com.example.store.request.item.ItemUpdate;
import com.example.store.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final FileUploadService fileUploadService;
    private final StockMapper stockMapper;

    public void addItem(ItemCreate itemCreate, MultipartFile itemImage) {
        validateItemCreate(itemCreate);
        String imagePath = fileUploadService.saveFile(itemImage);

        Item item = Item.builder()
                .name(itemCreate.getName())
                .price(itemCreate.getPrice())
                .info(itemCreate.getInfo())
                .itemImage(imagePath)
                .build();
        itemRepository.save(item);
    }

    public ItemDTO getItem(Long itemId) {
        Item item = itemRepository.findById(itemId);
        if (item == null) {
            throw new ItemNotFound(itemId);
        }
        return ItemDTO.fromEntity(item);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(ItemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemResponse> getList(ItemSearch itemSearch) {
        List<Item> page = itemRepository.findByPage(itemSearch.getSize(), itemSearch.getOffset(), itemSearch.isAsc(), itemSearch.getKeyword());
        return page.stream().map(ItemResponse::fromEntity).collect(Collectors.toList());
    }


    //수정
    @Override
    public void updateStock(Long itemId, ItemUpdate itemUpdate) {
        Item item = itemRepository.findById(itemId);
        if (item == null) {
            throw new ItemNotFound(itemId);
        }
        validateItemUpdate(itemUpdate);

        itemUpdate.setPrice(itemUpdate.getPrice());
        itemUpdate.setQuantity(itemUpdate.getQuantity()); // Ensure quantity is updated
        itemUpdate.setInfo(itemUpdate.getInfo());
        itemRepository.update(item);

        stockMapper.updateStock(itemId, itemUpdate.getQuantity());
    }

    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId);
        if (item == null) {
            throw new ItemNotFound(itemId);
        }
        itemRepository.delete(itemId);
    }

    private void validateItemCreate(ItemCreate itemCreate) {
        if (itemCreate.getName() == null || itemCreate.getName().isEmpty()) {
            InvalidItemException e = new InvalidItemException("아이템 데이터가 올바르지 않습니다.");
            e.addValidation("name", "이름이 비어있습니다.");
            throw e;
        }
        if (itemCreate.getPrice() == null || itemCreate.getPrice() <= 0) {
            InvalidItemException e = new InvalidItemException("아이템 데이터가 올바르지 않습니다.");
            e.addValidation("price", "가격이 0원보다 커야합니다.");
            throw e;
        }
    }

    private void validateItemUpdate(ItemUpdate itemUpdate) {
        if (itemUpdate.getPrice() == null || itemUpdate.getPrice() <= 0) {
            InvalidItemException e = new InvalidItemException("아이템 데이터가 올바르지 않습니다.");
            e.addValidation("price", "가격이 0원보다 커야합니다.");
            throw e;
        }
        if (itemUpdate.getQuantity() == null || itemUpdate.getQuantity() < 0) {
            InvalidItemException e = new InvalidItemException("아이템 데이터가 올바르지 않습니다.");
            e.addValidation("quantity", "재고량이 올바르지 않습니다.");
            throw e;
        }
    }

    public void addStock(Long itemId, int quantity) {
        stockMapper.addStock(itemId, quantity);
    }

    public void updateStock(Long itemId, int quantity) {
        stockMapper.updateStock(itemId, quantity);
    }
}
