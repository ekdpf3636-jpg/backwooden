package com.springboot.wooden.service;

import com.springboot.wooden.domain.Buyer;
import com.springboot.wooden.domain.Part;
import com.springboot.wooden.domain.PartOrder;
import com.springboot.wooden.dto.PartOrderRequestDto;
import com.springboot.wooden.dto.PartOrderResponseDto;
import com.springboot.wooden.repository.BuyerRepository;
import com.springboot.wooden.repository.PartOrderRepository;
import com.springboot.wooden.repository.PartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartOrderServiceImpl implements PartOrderService {

    private final PartOrderRepository partOrderRepository;
    private final BuyerRepository buyerRepository;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PartOrderResponseDto> getAll() {
        return partOrderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order, PartOrderResponseDto.class))
                .toList();
    }

    // 등록
    @Override
    @Transactional
    public PartOrderResponseDto addPartOrder(PartOrderRequestDto dto) {
        Buyer buyer = buyerRepository.findById(dto.getBuyerNo())
                .orElseThrow(() -> new EntityNotFoundException("Buyer not found: " + dto.getBuyerNo()));
        Part part = partRepository.findById(dto.getPartNo())
                .orElseThrow(() -> new EntityNotFoundException("Part not found: " + dto.getPartNo()));

        PartOrder order = modelMapper.map(dto, PartOrder.class);
        order.changeBuyer(buyer);
        order.changePart(part);

        PartOrder saved = partOrderRepository.save(order);
        return modelMapper.map(saved, PartOrderResponseDto.class);
    }

    // 수정
    @Override
    @Transactional
    public PartOrderResponseDto updatePartOrder(Long poNo, PartOrderRequestDto dto) {
        PartOrder order = partOrderRepository.findById(poNo)
                .orElseThrow(() -> new EntityNotFoundException("PartOrder not found: " + poNo));

        modelMapper.map(dto, order);

        if (dto.getBuyerNo() != null) {
            Buyer buyer = buyerRepository.findById(dto.getBuyerNo())
                    .orElseThrow(() -> new EntityNotFoundException("Buyer not found: " + dto.getBuyerNo()));
            order.changeBuyer(buyer);
        }
        if (dto.getPartNo() != null) {
            Part part = partRepository.findById(dto.getPartNo())
                    .orElseThrow(() -> new EntityNotFoundException("Part not found: " + dto.getPartNo()));
            order.changePart(part);
        }

        return modelMapper.map(order, PartOrderResponseDto.class);
    }

    // 삭제
    @Override
    @Transactional
    public void deletePartOrder(Long id) {
        partOrderRepository.deleteById(id);
    }
}
