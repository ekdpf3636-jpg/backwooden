package com.springboot.wooden.service;

import com.springboot.wooden.domain.Buyer;
import com.springboot.wooden.dto.BuyerDTO;
import com.springboot.wooden.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository repo;

    @Override
    public BuyerDTO save(BuyerDTO dto) {
        Buyer saved = repo.save(dto.toEntity());
        return BuyerDTO.fromEntity(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BuyerDTO> findAll() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "buyerNo"))
                .stream()
                .map(BuyerDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BuyerDTO findById(Long id) {
        Buyer buyer = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("구매거래처가 존재하지 않습니다. ID=" + id));
        return BuyerDTO.fromEntity(buyer);
    }

    @Override
    public BuyerDTO update(Long id, BuyerDTO dto) {
        Buyer b = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("수정 대상이 존재하지 않습니다. ID=" + id));

        b.setBuyerComp(dto.getBuyerComp());
        b.setBuyerName(dto.getBuyerName());
        b.setBuyerEmail(dto.getBuyerEmail());
        b.setBuyerPhone(dto.getBuyerPhone());
        b.setBuyerAddr(dto.getBuyerAddr());

        return BuyerDTO.fromEntity(repo.save(b));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("삭제할 구매거래처가 존재하지 않습니다. ID=" + id);
        }
        repo.deleteById(id);
    }
}
