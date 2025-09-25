package com.springboot.wooden.service;

import com.springboot.wooden.domain.Item;
import com.springboot.wooden.dto.ItemRequestDto;
import com.springboot.wooden.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;

    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    // 전체 조회
    @Override
    public List<Item> getAll() {
        return repository.findAll();
    }

    // 상품명으로 조회
    @Override
    public Optional<Item> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Item register(ItemRequestDto dto) {
        Item item = new Item();
        item.setCode(dto.getCode());
        item.setName(dto.getName());
        item.setSpec(dto.getSpec());
        item.setPrice(dto.getPrice());
        return repository.save(item);
    }

    @Override
    public Item update(Long id, ItemRequestDto dto) {
        Item existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        existing.setCode(dto.getCode());
        existing.setName(dto.getName());
        existing.setSpec(dto.getSpec());
        existing.setPrice(dto.getPrice());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
