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
        Buyer buyer = mapper.map(dto, Buyer.class);
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

    @Override
    public BuyerResponseDto update(Long id, BuyerRequestDto dto) {
        Buyer buyer = repo.findById(id).orElseThrow();
        mapper.map(dto, buyer); // PK 유지하며 값만 덮어쓰기
        Buyer saved = repo.save(buyer);
        return mapper.map(saved, BuyerResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
