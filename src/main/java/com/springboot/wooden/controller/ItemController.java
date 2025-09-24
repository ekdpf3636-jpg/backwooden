// src/main/java/com/springboot/wooden/controller/ItemController.java
package com.springboot.wooden.controller;

import com.springboot.wooden.dto.ItemDTO;
import com.springboot.wooden.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/item") // 기존 경로 유지
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    private final ItemService service;

    // 🔹 전체 목록 (페이징X)
    @GetMapping
    public List<ItemDTO> getAll() {
        return service.getAll();
    }



    // 🔹 단건 조회
    @GetMapping("/{itemNo}")
    public ResponseEntity<ItemDTO> get(@PathVariable Long itemNo) {
        return service.getOne(itemNo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 등록
    @PostMapping
    public Map<String, Long> add(@RequestBody ItemDTO itemDTO) {
        log.info("ItemDTO(add): {}", itemDTO);
        Long itemNo = service.addItem(itemDTO);
        return Map.of("ITEMNO", itemNo);
    }

    // 🔹 수정
    @PutMapping("/{itemNo}")
    public Map<String, String> modify(@PathVariable Long itemNo, @RequestBody ItemDTO itemDTO) {
        itemDTO.setItemNo(itemNo);
        service.modify(itemDTO);
        return Map.of("RESULT", "SUCCESS");
    }

    // 🔹 삭제
    @DeleteMapping("/{itemNo}")
    public Map<String, String> remove(@PathVariable Long itemNo) {
        service.remove(itemNo);
        return Map.of("RESULT", "SUCCESS");
    }
}
