package com.springboot.wooden.controller;

import com.springboot.wooden.dto.PlanRequestDTO;
import com.springboot.wooden.dto.PlanResponseDTO;
import com.springboot.wooden.service.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/plan/planlist")
@RequiredArgsConstructor
public class PlanController {

    private  final PlanService planService;

    @GetMapping
    public List<PlanResponseDTO> getAllPlan() {
        return planService.getAll();
    }

    // 등록 (POST /api/plan/planlist) - 폼/AJAX로 호출, URL 변화 없음
    @PostMapping
    public ResponseEntity<PlanResponseDTO> createPlan(@RequestBody @Valid PlanRequestDTO dto) {
        PlanResponseDTO saved = planService.save(dto);
        return ResponseEntity.ok(saved); // 생성 후 조회해서 DTO로 반환
    }

    // plan 수정
    @PutMapping
    public ResponseEntity<PlanResponseDTO> update(@RequestBody @Valid PlanRequestDTO dto) {
        Long id = dto.getPlanNo();
        PlanResponseDTO updated = planService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody PlanRequestDTO dto){
        Long id = dto.getPlanNo();
        planService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
