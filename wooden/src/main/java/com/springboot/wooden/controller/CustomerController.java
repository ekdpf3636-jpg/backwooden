package com.springboot.wooden.controller;


import com.springboot.wooden.domain.Customer;
import com.springboot.wooden.dto.CustomerRequestDto;
import com.springboot.wooden.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer") // 공통 API
@CrossOrigin(origins = "http://localhost:3000") // React 와 연동
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }
    // 목록 조회
    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAll();
    }

    // 단건 조회
    @GetMapping("/company/{company}")
    public Customer getCustomer(@PathVariable String company) {
        return service.getByCompany(company).
                orElseThrow(() -> new RuntimeException("Customer not found with company : " + company));
    }

    // 등록 -> CustomerAddPage
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerRequestDto dto) {
        Customer saved = service.register(dto);
        return ResponseEntity.ok(saved);
    }

    // 수정 -> CustomerModifyPage
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,
                                                   @Valid @RequestBody CustomerRequestDto dto) {
        Customer updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 삭제 -> CustomerIndexPage
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }




}





















