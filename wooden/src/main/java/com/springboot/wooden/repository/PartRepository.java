package com.springboot.wooden.repository;

import com.springboot.wooden.domain.Part;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {

    @EntityGraph(attributePaths = "buyer")                 // 추가
    Optional<Part> findByBuyer_BuyerNo(Long buyerNo);      // 추가 (1:1이므로 Optional)
}
