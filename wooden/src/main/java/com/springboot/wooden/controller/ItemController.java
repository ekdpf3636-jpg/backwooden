package com.springboot.wooden.controller;

import com.springboot.wooden.dto.BuyerResponseDto;
import com.springboot.wooden.dto.ItemRequestDto;
import com.springboot.wooden.dto.ItemResponseDto;
import com.springboot.wooden.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plan/itemlist")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    @GetMapping
    public List<ItemResponseDto> getAllItems() {
        return service.getAll();
    }

    // 추가
    @PostMapping
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody @Valid ItemRequestDto dto) {
        ItemResponseDto saved = service.save(dto);
        return ResponseEntity.ok(saved);
    }

    // 수정
    @PutMapping
    public ResponseEntity<ItemResponseDto> update(@Valid @RequestBody ItemRequestDto dto) {
        Long id = dto.getItemNo();
        ItemResponseDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping
    public ResponseEntity<ItemResponseDto> delete(@RequestBody ItemRequestDto dto) {
        Long id = dto.getItemNo();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
