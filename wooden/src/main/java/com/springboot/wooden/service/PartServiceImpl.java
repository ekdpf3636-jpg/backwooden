package com.springboot.wooden.service;

import com.springboot.wooden.domain.Buyer;
import com.springboot.wooden.domain.Part;
import com.springboot.wooden.dto.PartRequestDto;
import com.springboot.wooden.dto.PartResponseDto;
import com.springboot.wooden.repository.BuyerRepository;
import com.springboot.wooden.repository.PartRepository;
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


    @Override
    @Transactional(readOnly = true)
    public List<PartResponseDto> getAllParts() {
        // 최신 등록 순 정렬 (엔티티 필드명이 partNo라면 "partNo" 사용)
        List<Part> parts = partRepository.findAll(Sort.by(Sort.Direction.DESC, "partNo"));

        return parts.stream()
                .map(p -> {
                    PartResponseDto dto = modelMapper.map(p, PartResponseDto.class);
                    // 구매처명 세팅 (null 안전)
                    dto.setBuyerComp(p.getBuyer() != null ? p.getBuyer().getBuyerComp() : null);
                    return dto;
                })
                .toList();
    }

    @Override
    @Transactional
    public Long addPart(PartRequestDto dto) {
        Buyer buyer = buyerRepository.findById(dto.getBuyerNo())
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found: " + dto.getBuyerNo()));

        Part part = modelMapper.map(dto, Part.class);
        part.changeBuyer(buyer);

        return partRepository.save(part).getPartNo();
    }

    @Override
    @Transactional(readOnly = true)
    public PartResponseDto getOne(Long partNo) {
        Part part = partRepository.findById(partNo)
                .orElseThrow(() -> new IllegalArgumentException("Part not found: " + partNo));

        PartResponseDto dto = modelMapper.map(part, PartResponseDto.class);
        dto.setBuyerComp(part.getBuyer().getBuyerComp()); // 구매처명 세팅
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public PartResponseDto getByBuyerNo(Long buyerNo) {
        Part part = partRepository.findByBuyer_BuyerNo(buyerNo)
                .orElseThrow(() -> new IllegalArgumentException("Part not found for buyerNo: " + buyerNo));

        PartResponseDto dto = modelMapper.map(part, PartResponseDto.class);
        dto.setBuyerComp(part.getBuyer().getBuyerComp());
        return dto;
    }

    @Override
    @Transactional
    public void updatePart(Long partNo, PartRequestDto dto) {
        Part part = partRepository.findById(partNo)
                .orElseThrow(() -> new IllegalArgumentException("Part not found: " + partNo));

        // ModelMapper로 덮어쓰기
        modelMapper.map(dto, part);

        if (dto.getBuyerNo() != null) {
            Buyer buyer = buyerRepository.findById(dto.getBuyerNo())
                    .orElseThrow(() -> new IllegalArgumentException("Buyer not found: " + dto.getBuyerNo()));
            part.changeBuyer(buyer);
        }
    }

    @Override
    @Transactional
    public void deletePart(Long partNo) {
        if (!partRepository.existsById(partNo)) {
            throw new IllegalArgumentException("Part not found: " + partNo);
        }
        partRepository.deleteById(partNo);
    }
}
