package com.springboot.wooden.controller;

import com.springboot.wooden.dto.PartOrderRequestDto;
import com.springboot.wooden.dto.PartOrderResponseDto;
import com.springboot.wooden.service.PartOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyer/partorder")  // 하이픈 제거, 카멜케이스
@RequiredArgsConstructor
public class PartOrderController {

    private final PartOrderService partOrderService;

    // 전체 발주 조회
    @GetMapping
    public List<PartOrderResponseDto> getAllPartOrders() {
        return partOrderService.getAll();
    }

    // 발주 등록
    @PostMapping
    public ResponseEntity<PartOrderResponseDto> createPartOrder(@RequestBody @Valid PartOrderRequestDto dto) {
        PartOrderResponseDto saved = partOrderService.addPartOrder(dto);
        return ResponseEntity.ok(saved);
    }

    // 발주 수정
    @PutMapping
    public ResponseEntity<PartOrderResponseDto> updatePartOrder(@RequestBody @Valid  PartOrderRequestDto dto    ) {
        Long id = dto.getPoNo();
        PartOrderResponseDto updated = partOrderService.updatePartOrder(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 발주 삭제
    @DeleteMapping
    public ResponseEntity<Void> deletePartOrder(@RequestBody PartOrderRequestDto dto) {
        Long id = dto.getPoNo();
        partOrderService.deletePartOrder(id);
        return ResponseEntity.noContent().build();
    }
}
