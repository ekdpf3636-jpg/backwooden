package com.springboot.wooden.repository;

import com.springboot.wooden.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 - JPA가 기본 CRUD를 제공
 - findAll(), finById(), save(), deleteById() 등
 */

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
