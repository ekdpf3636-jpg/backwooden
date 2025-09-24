// src/main/java/com/springboot/wooden/service/ItemService.java
package com.springboot.wooden.service;

import com.springboot.wooden.dto.ItemDTO;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Long addItem(ItemDTO itemDTO);
    Optional<ItemDTO> getOne(Long itemNo);
    void modify(ItemDTO itemDTO);
    void remove(Long itemNo);

    // 🔹 페이징 대신 전체 목록
    List<ItemDTO> getAll();
}