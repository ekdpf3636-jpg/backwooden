package com.springboot.wooden.service;

import com.springboot.wooden.dto.BuyerDTO;
import java.util.List;

public interface BuyerService {
    BuyerDTO save(BuyerDTO dto);
    List<BuyerDTO> findAll();
    BuyerDTO findById(Long id);
    BuyerDTO update(Long id, BuyerDTO dto);
    void delete(Long id);
}
