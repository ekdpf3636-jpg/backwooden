package com.springboot.wooden.repository;

import com.springboot.wooden.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByCustomer_Company(String company); // Customer 엔티티의 company 필드 기준으로 검색
}
