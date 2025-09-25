package com.springboot.wooden.service;

import com.springboot.wooden.domain.Item;
import com.springboot.wooden.dto.ItemRequestDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> getAll();            // 전체 조회
    Optional<Item> getByName(String name);  // 상품명으로 조회
    Item register(ItemRequestDto dto);      // 등록
    Item update(Long id, ItemRequestDto dto);    // 수정
    void delete(Long id);       // 삭제
}
