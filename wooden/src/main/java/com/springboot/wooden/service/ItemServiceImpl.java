package com.springboot.wooden.service;

import com.springboot.wooden.domain.Buyer;
import com.springboot.wooden.domain.Item;
import com.springboot.wooden.dto.BuyerRequestDto;
import com.springboot.wooden.dto.BuyerResponseDto;
import com.springboot.wooden.dto.ItemRequestDto;
import com.springboot.wooden.dto.ItemResponseDto;
import com.springboot.wooden.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<ItemResponseDto> getAll() {
        return repository.findAll().stream()
                .map(i -> modelMapper.map(i, ItemResponseDto.class))
                .toList();
    }

    @Override
    @Transactional
    public ItemResponseDto save(ItemRequestDto dto) {
        Item item = Item.builder()
                .itemCode(dto.getItemCode())
                .itemName(dto.getItemName())
                .itemSpec(dto.getItemSpec())
                .itemPrice(dto.getItemPrice())
                .build();

        Item saved = repository.save(item);
        return modelMapper.map(saved, ItemResponseDto.class);
    }
    @Override
    public Optional<ItemResponseDto> getByName(String name) {
        return repository.findByItemName(name)
                .map(i -> modelMapper.map(i, ItemResponseDto.class));
    }

    @Override
    @Transactional
    public ItemResponseDto register(ItemRequestDto dto) {
        Item item = Item.builder()
                .itemCode(dto.getItemCode())
                .itemName(dto.getItemName())
                .itemSpec(dto.getItemSpec())
                .itemPrice(dto.getItemPrice())
                .build();

        Item saved = repository.save(item);
        return modelMapper.map(saved, ItemResponseDto.class);
    }

    @Override
    @Transactional
    public ItemResponseDto update(Long id, ItemRequestDto dto) {
        Item existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        existing.changeItemName(dto.getItemName());
        existing.changeItemSpec(dto.getItemSpec());
        existing.changeItemPrice(dto.getItemPrice());
        existing.changeItemCode(dto.getItemCode());
        return modelMapper.map(existing, ItemResponseDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
