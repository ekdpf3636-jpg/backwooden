package com.springboot.wooden.controller;

import com.springboot.wooden.dto.PartRequestDto;
import com.springboot.wooden.dto.PartResponseDto;
import com.springboot.wooden.service.PartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyer/partlist")
@RequiredArgsConstructor
public class PartController {

    private final PartService partService;

    // 목록 조회
    @GetMapping
    public List<PartResponseDto> getAll() {
        return partService.getAll();
    }

    // 등록
    @PostMapping
    public ResponseEntity<PartResponseDto> add(@RequestBody @Valid PartRequestDto dto) {
        PartResponseDto saved = partService.save(dto);
        return ResponseEntity.ok(saved);
    }

//    // 구매처 기준 조회 (Add폼 자동 채움용)
//    @GetMapping
//    public ResponseEntity<PartResponseDto> getByBuyer(@PathVariable Long buyerNo) {
//        return ResponseEntity.ok(partService.getByBuyerNo(buyerNo));
//    }

    // 수정
    @PutMapping
    public ResponseEntity<PartResponseDto> update(@RequestBody @Valid PartRequestDto dto) {
        Long id = dto.getPartNo();
        PartResponseDto updated = partService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 삭제
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody PartRequestDto dto) {
        Long id = dto.getPartNo();
        partService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
