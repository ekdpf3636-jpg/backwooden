package com.springboot.wooden.service;

import com.springboot.wooden.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAll();                // 전체 조회

    Optional<Customer> getByCompany(String company); // 판매처명으로 조회

    Customer register(Customer customer);            // 등록 + 수정

    Customer update(Long id, Customer customer);     // 업데이트

    void delete(Long id);         // 삭제

}
