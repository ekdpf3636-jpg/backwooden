package com.springboot.wooden.ItemRepository;

import com.springboot.wooden.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // JpaRepository에 의해 findById, findAll, save 등 기본 메소드 제공
}
