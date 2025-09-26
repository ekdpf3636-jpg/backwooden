// src/main/java/com/springboot/wooden/controller/BuyerController.java
package com.springboot.wooden.controller;

import com.springboot.wooden.dto.BuyerRequestDto;
import com.springboot.wooden.dto.BuyerResponseDto;
import com.springboot.wooden.service.BuyerService;
import com.springboot.wooden.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyer/buyercustomer")
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService service;

    @GetMapping
    public ApiResponse<List<BuyerResponseDto>> list() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<BuyerResponseDto> get(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping("/add")
    public ApiResponse<BuyerResponseDto> add(@RequestBody @Valid BuyerRequestDto dto) {
        return ApiResponse.ok(service.save(dto));
    }

    @PutMapping("/modify/{id}")
    public ApiResponse<BuyerResponseDto> modify(@PathVariable Long id,
                                                @RequestBody @Valid BuyerRequestDto dto) {
        return ApiResponse.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok(null);
    }
}
