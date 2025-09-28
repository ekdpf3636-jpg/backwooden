package com.springboot.wooden.controller;

import com.springboot.wooden.dto.CustomerRequestDto;
import com.springboot.wooden.dto.CustomerResponseDto;
import com.springboot.wooden.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order/sellercustomer") // 공통 API
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    // 목록 조회
    @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody @Valid CustomerRequestDto dto) {
        CustomerResponseDto saved = service.register(dto);
        return ResponseEntity.ok(saved);
    }

    // 수정
    @PutMapping
    public ResponseEntity<CustomerResponseDto> update(@Valid @RequestBody CustomerRequestDto dto) {
        Long id = dto.getCusNo();
        CustomerResponseDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 삭제
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody CustomerRequestDto dto) {
        Long id = dto.getCusNo();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}