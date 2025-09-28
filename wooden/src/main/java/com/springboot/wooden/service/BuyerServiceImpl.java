package com.springboot.wooden.service;

import com.springboot.wooden.domain.Buyer;
import com.springboot.wooden.dto.BuyerRequestDto;
import com.springboot.wooden.dto.BuyerResponseDto;
import com.springboot.wooden.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository repo;
    private final ModelMapper mapper;

    @Override
    public BuyerResponseDto save(BuyerRequestDto dto) {
        Buyer buyer = Buyer.builder()
                .buyerComp(dto.getBuyerComp())
                .buyerName(dto.getBuyerName())
                .buyerEmail(dto.getBuyerEmail())
                .buyerPhone(dto.getBuyerPhone())
                .buyerAddr(dto.getBuyerAddr())
                .build();

        Buyer saved = repo.save(buyer);
        return mapper.map(saved, BuyerResponseDto.class);
    }


    @Override
    public List<BuyerResponseDto> findAll() {
        return repo.findAll().stream()
                .map(buyer -> mapper.map(buyer, BuyerResponseDto.class))
                .toList();
    }

    @Override
    public BuyerResponseDto findById(Long id) {
        Buyer buyer = repo.findById(id).orElseThrow(); // 필요 시 커스텀 예외로 교체
        return mapper.map(buyer, BuyerResponseDto.class);
    }

    public BuyerResponseDto update(Long id, BuyerRequestDto dto) {
        Buyer buyer = repo.findById(id).orElseThrow();

        // 부분 수정: null이면 건너뛰기
        if (dto.getBuyerComp()  != null) buyer.changeBuyerComp(dto.getBuyerComp());
        if (dto.getBuyerName()  != null) buyer.changeBuyerName(dto.getBuyerName());
        if (dto.getBuyerEmail() != null) buyer.changeBuyerEmail(dto.getBuyerEmail());
        if (dto.getBuyerPhone() != null) buyer.changeBuyerPhone(dto.getBuyerPhone());
        if (dto.getBuyerAddr()  != null) buyer.changeBuyerAddr(dto.getBuyerAddr());

        return mapper.map(buyer, BuyerResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
