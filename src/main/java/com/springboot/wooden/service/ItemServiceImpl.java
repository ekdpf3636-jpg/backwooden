// src/main/java/com/springboot/wooden/service/ItemServiceImpl.java
package com.springboot.wooden.service;

import com.springboot.wooden.ItemRepository.ItemRepository; // <- 너의 기존 패키지 유지
import com.springboot.wooden.domain.Item;
import com.springboot.wooden.dto.ItemDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;

    @Override
    public Long addItem(ItemDTO itemDTO){
        Item item = modelMapper.map(itemDTO, Item.class);
        Item saved = itemRepository.save(item);
        return saved.getItemNo();
    }

    @Override
    public Optional<ItemDTO> getOne(Long itemNo){
        return itemRepository.findById(itemNo)
                .map(entity -> modelMapper.map(entity, ItemDTO.class));
    }

    @Override
    public void modify(ItemDTO itemDTO){
        Item item = itemRepository.findById(itemDTO.getItemNo())
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemDTO.getItemNo()));

        item.changeItemCode(itemDTO.getItemCode());
        item.changeItemName(itemDTO.getItemName());
        item.changeItemSpec(itemDTO.getItemSpec());
        item.changeItemPrice(itemDTO.getItemPrice());
        // 트랜잭션 종료 시 JPA dirty checking 반영
    }

    @Override
    public void remove(Long itemNo){
        itemRepository.deleteById(itemNo);
    }

    @Override
    public List<ItemDTO> getAll() {
        return itemRepository.findAll(Sort.by(Sort.Direction.DESC, "itemNo"))
                .stream()
                .map(entity -> modelMapper.map(entity, ItemDTO.class))
                .toList();
    }
}
