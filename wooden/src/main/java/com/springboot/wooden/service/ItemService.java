package com.springboot.wooden.service;

import com.springboot.wooden.dto.BuyerRequestDto;
import com.springboot.wooden.dto.BuyerResponseDto;
import com.springboot.wooden.dto.ItemRequestDto;
import com.springboot.wooden.dto.ItemResponseDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    ItemResponseDto save(ItemRequestDto requestDto);
    List<ItemResponseDto> getAll();                  // 전체 조회 → DTO
    Optional<ItemResponseDto> getByName(String name);// 단건 조회 → DTO
    ItemResponseDto register(ItemRequestDto dto);    // 등록 → DTO
    ItemResponseDto update(Long id, ItemRequestDto dto); // 수정 → DTO
    void delete(Long id);
}
