package com.springboot.wooden.controller;

import com.springboot.wooden.dto.PartRequestDto;
import com.springboot.wooden.dto.PartResponseDto;
import com.springboot.wooden.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/part")
@RequiredArgsConstructor
public class PartController {

    private final PartService partService;

    // 목록 조회
    @GetMapping
    public ResponseEntity<List<PartResponseDto>> getAll() {
        return ResponseEntity.ok(partService.getAllParts());
    }

    // 등록
    @PostMapping
    public ResponseEntity<Long> add(@RequestBody PartRequestDto dto) {
        return ResponseEntity.ok(partService.addPart(dto));
    }

    // 단건 조회
    @GetMapping("/{partNo}")
    public ResponseEntity<PartResponseDto> getOne(@PathVariable Long partNo) {
        return ResponseEntity.ok(partService.getOne(partNo));
    }

    // 구매처 기준 조회 (Add폼 자동 채움용)
    @GetMapping("/buyer/{buyerNo}")
    public ResponseEntity<PartResponseDto> getByBuyer(@PathVariable Long buyerNo) {
        return ResponseEntity.ok(partService.getByBuyerNo(buyerNo));
    }

    // 수정
    @PutMapping("/{partNo}")
    public ResponseEntity<Void> update(@PathVariable Long partNo, @RequestBody PartRequestDto dto) {
        partService.updatePart(partNo, dto);
        return ResponseEntity.noContent().build();
    }

    // 삭제
    @DeleteMapping("/{partNo}")
    public ResponseEntity<Void> delete(@PathVariable Long partNo) {
        partService.deletePart(partNo);
        return ResponseEntity.noContent().build();
    }
}
