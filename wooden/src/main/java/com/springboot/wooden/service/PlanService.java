package com.springboot.wooden.service;

import com.springboot.wooden.dto.PlanRequestDTO;

import java.util.List;


public interface PlanService {

    Long addPlan(PlanRequestDTO planDTO);  // DTO를 받아서 저장
    PlanRequestDTO get(Long tno);          // DTO 반환
    List<PlanRequestDTO> getAll();
    void modify(PlanRequestDTO planDTO);   //
}
