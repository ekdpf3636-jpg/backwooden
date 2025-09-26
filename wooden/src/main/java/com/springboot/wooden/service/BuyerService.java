package com.springboot.wooden.service;

import com.springboot.wooden.dto.BuyerRequestDto;
import com.springboot.wooden.dto.BuyerResponseDto;

import java.util.List;

public interface BuyerService {

    BuyerResponseDto save(BuyerRequestDto requestDto);
    List<BuyerResponseDto> findAll();
    BuyerResponseDto findById(Long id);
    BuyerResponseDto update(Long id, BuyerRequestDto requestDto);
    void delete(Long id);

}
