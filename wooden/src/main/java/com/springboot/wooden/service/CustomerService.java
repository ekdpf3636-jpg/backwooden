package com.springboot.wooden.service;

import com.springboot.wooden.domain.Customer;
import com.springboot.wooden.dto.CustomerRequestDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAll();                // 전체 조회

    Optional<Customer> getByCompany(String company); // 판매처명으로 조회

    Customer register(CustomerRequestDto dto);            // 등록 + 수정

    Customer update(Long id, CustomerRequestDto dto);     // 업데이트

    void delete(Long id);         // 삭제

}
