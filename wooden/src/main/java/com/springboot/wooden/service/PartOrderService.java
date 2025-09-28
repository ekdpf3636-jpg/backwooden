package com.springboot.wooden.service;

import com.springboot.wooden.dto.PartOrderRequestDto;
import com.springboot.wooden.dto.PartOrderResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface PartOrderService {

    List<PartOrderResponseDto> getAll();                    // 전체 조회
    PartOrderResponseDto addPartOrder(PartOrderRequestDto dto);           // 등록
    PartOrderResponseDto updatePartOrder(Long poNo, PartOrderRequestDto dto); // 수정
    void deletePartOrder(Long poNo);                        // 삭제
}
