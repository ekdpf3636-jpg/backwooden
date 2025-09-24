package com.springboot.wooden.service;

import com.springboot.wooden.domain.Customer;
import com.springboot.wooden.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    // 생성자 주입 (스프링에서 CustomerRepository 구현체 자동 주입)
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    // 전체 조회
    @Override
    public List<Customer> getAll() {
        return repository.findAll();  // SELECT * FROM CUSTOMER_TBL
    }

    // 단건 조회
    @Override
    public Optional<Customer> getByCompany(String company) {
        return repository.findByCompany(company);  // SELECT * FROM CUSTOMER_TBL WHERE cus_comp = ?
    }

    @Override
    public Customer register(Customer customer) {
        return repository.save(customer);
    }

    // 판매거래처 등록
    @Override
    public Customer update(Long id, Customer customer) {

        // DB 에서 기존 고객 찾기
        Customer existing = repository.findById(id).
                orElseThrow(() -> new RuntimeException("Customer not found"));

        // 기존 데이터에 새로운 값 덮어 쓰기
        existing.setCompany(customer.getCompany());
        existing.setManager(customer.getManager());
        existing.setPhone(customer.getPhone());
        existing.setAddress(customer.getAddress());

        // 저장된 값이 있으면 UPDATE 실행
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);  // DELETE FROM CUSTOMER_TBL WHERE cus_no = ?
    }

}


















