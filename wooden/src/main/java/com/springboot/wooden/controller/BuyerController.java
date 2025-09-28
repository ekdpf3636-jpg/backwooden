// src/main/java/com/springboot/wooden/controller/BuyerController.java
package com.springboot.wooden.controller;

import com.springboot.wooden.dto.BuyerRequestDto;
import com.springboot.wooden.dto.BuyerResponseDto;
import com.springboot.wooden.service.BuyerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyer/buyercustomer")
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService buyerService;

    // 목록 조회
    @GetMapping
    public List<BuyerResponseDto> list() {
        return buyerService.findAll();
    }

    // 추가
    @PostMapping
    public ResponseEntity<BuyerResponseDto> createBuyer(@RequestBody @Valid BuyerRequestDto dto) {
        BuyerResponseDto saved = buyerService.save(dto);
        return ResponseEntity.ok(saved);
    }

    // 수정
    @PutMapping
    public ResponseEntity<BuyerResponseDto> update(@RequestBody @Valid BuyerRequestDto dto) {
        Long id = dto.getBuyerNo();
        BuyerResponseDto updated = buyerService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 삭제
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody BuyerRequestDto dto) {
        Long id = dto.getBuyerNo();
        buyerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
