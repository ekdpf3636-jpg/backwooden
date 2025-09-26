package com.springboot.wooden.service;

import com.springboot.wooden.domain.Plan;
import com.springboot.wooden.dto.PlanRequestDTO;
import com.springboot.wooden.repository.PlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Transactional
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final ModelMapper modelMapper;
    private final PlanRepository planRepository;

    @Override
    public Long addPlan(PlanRequestDTO planDTO) {
        log.info("add plan");
        Plan plan = modelMapper.map(planDTO, Plan.class);
        Plan savedPlan = planRepository.save(plan);
        return savedPlan.getPlanNo();
    }

    @Override
    public PlanRequestDTO get(Long tno) {
        Plan plan = planRepository.findById(tno).orElseThrow();
        return modelMapper.map(plan, PlanRequestDTO.class);
    }

    @Override
    public List<PlanRequestDTO> getAll() {
        return planRepository.findAll()
                .stream()
                .map(plan -> modelMapper.map(plan, PlanRequestDTO.class))
                .toList();

    }
    @Override
    public void modify(PlanRequestDTO planDTO) {
        Plan plan = planRepository.findById(planDTO.getPlanNo())
                .orElseThrow();

        // 엔티티에 정의된 변경 메서드만 사용
        plan.changePlanState(planDTO.getPlanState());
//        plan.changePlanStartDate(planDTO.getPlanStartDate());
//        plan.changePlanEndDate(planDTO.getPlanEndDate());

        // JPA 영속성 컨텍스트에 의해 자동으로 update 처리됨
        log.info("Modified plan: {}", plan.getPlanNo());


    }

}

