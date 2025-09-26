package com.springboot.wooden.controller;

import com.springboot.wooden.dto.PlanRequestDTO;
import com.springboot.wooden.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/plan")
@RequiredArgsConstructor

public class PlanController {

    private  final PlanService planService;

    @GetMapping("/planlist")
    public List<PlanRequestDTO> getAllPlan() {
        return planService.getAll();
    }

    // getMapping 필요
    @GetMapping("/{tno}")
    public PlanRequestDTO getPlan(@PathVariable Long tno) {
        return  planService.get(tno);
    }

    // plan 등록
    @PostMapping("/planlist")
    public Long addPlan(@RequestBody PlanRequestDTO planDTO){
        return planService.addPlan(planDTO);
    }

    // plan 수정
    @PutMapping
    public void modifyPlan(@RequestBody PlanRequestDTO planDTO) {
        planService.modify(planDTO);
    }



}
