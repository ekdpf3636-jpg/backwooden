package com.springboot.wooden.service;


import com.springboot.wooden.dto.PartRequestDto;
import com.springboot.wooden.dto.PartResponseDto;

import java.util.List;

public interface PartService {

    Long addPart(PartRequestDto dto);           // 등록

    PartResponseDto getOne(Long partNo);        // 단건 조회 (구매처명 포함)

    PartResponseDto getByBuyerNo(Long buyerNo); // 구매처 기준 조회

    void updatePart(Long partNo, PartRequestDto dto); // 수정

    void deletePart(Long partNo);               // 삭제

    List<PartResponseDto> getAllParts();
}
