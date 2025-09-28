package com.springboot.wooden.service;

import com.springboot.wooden.domain.Buyer;
import com.springboot.wooden.domain.Part;
import com.springboot.wooden.dto.PartRequestDto;
import com.springboot.wooden.dto.PartResponseDto;
import com.springboot.wooden.repository.BuyerRepository;
import com.springboot.wooden.repository.PartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final BuyerRepository buyerRepository;
    private final ModelMapper modelMapper;

    // -----------------------------
    // 등록
    // -----------------------------
    @Override
    @Transactional
    public PartResponseDto save(PartRequestDto dto) {
        Buyer buyer = buyerRepository.findById(dto.getBuyerNo())
                .orElseThrow(() -> new EntityNotFoundException("Buyer not found: " + dto.getBuyerNo()));

        // 한 Buyer당 Part 하나 1:1
        if (partRepository.existsByBuyer_BuyerNo(dto.getBuyerNo())) {
            throw new IllegalStateException("해당 구매거래처에는 이미 부품이 등록되어 있습니다. buyerNo=" + dto.getBuyerNo());
        }

        // 값 매핑
        Part part = Part.builder()
                .buyer(buyer)
                .partCode(dto.getPartCode())
                .partName(dto.getPartName())
                .partSpec(dto.getPartSpec())
                .partPrice(dto.getPartPrice())
                .build();

        Part saved = partRepository.save(part);
        return toResponse(saved);
    }

    // -----------------------------
    // 수정
    // -----------------------------
    @Override
    @Transactional
    public PartResponseDto update(Long partNo, PartRequestDto dto) {
        Part part = partRepository.findById(partNo)
                .orElseThrow(() -> new EntityNotFoundException("Part not found: " + partNo));

        // 연관 바꾸기(옵션): buyerNo가 왔다면 변경
        if (dto.getBuyerNo() != null) {
            Buyer buyer = buyerRepository.findById(dto.getBuyerNo())
                    .orElseThrow(() -> new EntityNotFoundException("Buyer not found: " + dto.getBuyerNo()));

            // 1:1 제약 지키기: 다른 Part가 이미 이 Buyer를 쓰고 있으면 불가
            // (현재 part 자신이 아닌, 동일 buyerNo를 쓰는 다른 엔티티가 있는지 검사)
            partRepository.findByBuyer_BuyerNo(dto.getBuyerNo()).ifPresent(existing -> {
                if (!existing.getPartNo().equals(partNo)) {
                    throw new IllegalStateException("이미 해당 구매거래처에 연결된 부품이 존재합니다. buyerNo=" + dto.getBuyerNo());
                }
            });

            part.changeBuyer(buyer);
        }

        // 값 필드 변경 (DTO에 값이 들어온 경우만)
        // 가격에 0은 금지
        if (dto.getPartCode() != null) part.changePartCode(dto.getPartCode());
        if (dto.getPartName() != null) part.changePartName(dto.getPartName());
        if (dto.getPartSpec() != null) part.changePartSpec(dto.getPartSpec());
        if (dto.getPartPrice() != 0) part.changePartPrice(dto.getPartPrice());

        return toResponse(part);
    }

    // -----------------------------
    // 삭제
    // -----------------------------
    @Override
    @Transactional
    public void delete(Long partNo) {
        if (!partRepository.existsById(partNo)) {
            throw new EntityNotFoundException("Part not found: " + partNo);
        }
        partRepository.deleteById(partNo);
    }

    // -----------------------------
    // 조회
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public PartResponseDto getOne(Long partNo) {
        Part part = partRepository.findById(partNo)
                .orElseThrow(() -> new EntityNotFoundException("Part not found: " + partNo));
        return toResponse(part);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartResponseDto> getAll() {
        return partRepository.findAll(Sort.by(Sort.Direction.DESC, "partNo"))
                .stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PartResponseDto getByBuyerNo(Long buyerNo) {
        Part part = partRepository.findByBuyer_BuyerNo(buyerNo)
                .orElseThrow(() -> new EntityNotFoundException("Part not found for buyerNo: " + buyerNo));
        return toResponse(part);
    }

    // -----------------------------
    // 매핑 헬퍼 buyerComp를 ResponseDto에 넣기 위해서 추가
    // -----------------------------
    private PartResponseDto toResponse(Part part) {
        PartResponseDto dto = modelMapper.map(part, PartResponseDto.class);
        dto.setBuyerComp(part.getBuyer() != null ? part.getBuyer().getBuyerComp() : null);
        return dto;
    }
}
