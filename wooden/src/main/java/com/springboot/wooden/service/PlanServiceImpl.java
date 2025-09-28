package com.springboot.wooden.service;

import com.springboot.wooden.domain.Item;
import com.springboot.wooden.domain.Plan;
import com.springboot.wooden.dto.PlanRequestDTO;
import com.springboot.wooden.dto.PlanResponseDTO;
import com.springboot.wooden.repository.ItemRepository;
import com.springboot.wooden.repository.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final ModelMapper modelMapper;
    private final PlanRepository planRepository;
    private final ItemRepository itemRepository;

    // 목록 조회
    @Override
    @Transactional(readOnly = true)
    public List<PlanResponseDTO> getAll() {
        return planRepository.findAll(Sort.by(Sort.Direction.DESC, "planNo"))
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // 등록
    @Override
    @Transactional
    public PlanResponseDTO save(PlanRequestDTO dto) {
        // 1) 기본 값 매핑
        Plan plan = modelMapper.map(dto, Plan.class);

        // 2) 연관 연결 (itemNo가 있어야 함)
        if (dto.getItemNo() == null) {
            throw new IllegalArgumentException("itemNo는 필수입니다.");
        }
        Item item = itemRepository.findById(dto.getItemNo())
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + dto.getItemNo()));
        plan.changeItem(item);

        // 3) 저장
        Plan saved = planRepository.save(plan);
        return toResponse(saved);
    }

    // 수정 (Body의 planNo 사용)
    @Override
    @Transactional
    public PlanResponseDTO update(Long planNo, PlanRequestDTO dto) {
        Plan plan = planRepository.findById(planNo)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found: " + planNo));

        // 값 갱신 (PlanRequestDTO의 타입이 기본형 int이므로 그대로 덮어씀)
        plan.changePlanState(dto.getPlanState());
        plan.changePlanStartDate(dto.getPlanStartDate());
        plan.changePlanEndDate(dto.getPlanEndDate());
        plan.changePlanQty(dto.getPlanQty());

        // 연관 변경(옵션): itemNo가 전달된 경우에만 변경
        if (dto.getItemNo() != null) {
            Item item = itemRepository.findById(dto.getItemNo())
                    .orElseThrow(() -> new EntityNotFoundException("Item not found: " + dto.getItemNo()));
            plan.changeItem(item);
        }

        return toResponse(plan); // 영속 상태 → 트랜잭션 종료 시 UPDATE 반영
    }

    // 삭제
    @Override
    @Transactional
    public void delete(Long planNo) {
        if (!planRepository.existsById(planNo)) {
            throw new EntityNotFoundException("Plan not found: " + planNo);
        }
        planRepository.deleteById(planNo);
    }

    // -----------------------------
    // 매핑 헬퍼: 엔티티 → 응답 DTO
    // -----------------------------
    private PlanResponseDTO toResponse(Plan plan) {
        PlanResponseDTO res = modelMapper.map(plan, PlanResponseDTO.class);
        res.setItemName(plan.getItem() != null ? plan.getItem().getItemName() : null);
        return res;
    }
}
