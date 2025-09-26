package com.springboot.wooden.controller;

import com.springboot.wooden.dto.ItemRequestDto;
import com.springboot.wooden.dto.ItemResponseDto;
import com.springboot.wooden.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<ItemResponseDto> getAllItems() {
        return service.getAll();
    }

    // 상품명으로 단건 조회
    @GetMapping("/name/{name}")
    public ItemResponseDto getItem(@PathVariable String name) {
        return service.getByName(name)
                .orElseThrow(() -> new RuntimeException("Item not found with itemName: " + name));
    }

    @PostMapping
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody @Valid ItemRequestDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDto> updateItem(@PathVariable Long id,
                                                      @Valid @RequestBody ItemRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemResponseDto> deleteItem(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
