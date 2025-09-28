package com.springboot.wooden.service;

import com.springboot.wooden.dto.BuyerRequestDto;
import com.springboot.wooden.dto.BuyerResponseDto;
import com.springboot.wooden.dto.OrderRequestDto;
import com.springboot.wooden.dto.OrderResponseDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderResponseDto save(OrderRequestDto requestDto);
    List<OrderResponseDto> getAllOrders();                   // 전체 조회
    Optional<OrderResponseDto> getByCompany(String company); // 판매처명으로 조회
    OrderResponseDto update(Long id, OrderRequestDto dto);   // 수정
    void delete(Long id);                              // 삭제
}
