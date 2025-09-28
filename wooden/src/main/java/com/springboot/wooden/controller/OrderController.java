package com.springboot.wooden.controller;

import com.springboot.wooden.dto.OrderRequestDto;
import com.springboot.wooden.dto.OrderResponseDto;
import com.springboot.wooden.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;
import java.security.Provider;
import java.util.List;

@RestController // REST API 컨트롤러 (JSON 반환)
@RequestMapping("/api/order/orderlist")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    // 전체 주문 조회
    @GetMapping
    public List<OrderResponseDto> getOrders() {
        return service.getAllOrders();
    }

    // 주문 등록
    @PostMapping
    public ResponseEntity<OrderResponseDto> addOrder(@RequestBody @Valid OrderRequestDto dto) {
        OrderResponseDto saved = service.save(dto);
        return ResponseEntity.ok(saved);
    }

    // 주문 수정
    @PutMapping
    public ResponseEntity<OrderResponseDto> update(@RequestBody @Valid OrderRequestDto dto){
        Long id = dto.getOrderNo();
        OrderResponseDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 주문 삭제
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody OrderRequestDto dto){
        Long id = dto.getOrderNo();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
