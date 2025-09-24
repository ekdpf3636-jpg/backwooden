package com.springboot.wooden.controller;


import com.springboot.wooden.domain.Customer;
import com.springboot.wooden.service.CustomerService;
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
    public Customer createCustomer(@RequestBody Customer customer) {
        return service.register(customer);
    }

    // 수정 -> CustomerModifyPage
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return service.update(id, customer);
    }

    // 삭제 -> CustomerIndexPage
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        service.delete(id);
    }




}





















