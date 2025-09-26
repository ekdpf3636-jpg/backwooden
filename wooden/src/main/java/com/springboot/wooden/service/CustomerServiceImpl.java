package com.springboot.wooden.service;

import com.springboot.wooden.domain.Customer;
import com.springboot.wooden.dto.CustomerRequestDto;
import com.springboot.wooden.dto.CustomerResponseDto;
import com.springboot.wooden.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<CustomerResponseDto> getAll() {
        return repository.findAll().stream()
                .map(c -> modelMapper.map(c, CustomerResponseDto.class))
                .toList();
    }

    @Override
    public Optional<CustomerResponseDto> getByCompany(String company) {
        return repository.findByCusComp(company)
                .map(c -> modelMapper.map(c, CustomerResponseDto.class));
    }

    @Override
    @Transactional
    public CustomerResponseDto register(CustomerRequestDto dto) {
        Customer c = Customer.builder()
                .cusComp(dto.getCusComp())
                .cusName(dto.getCusName())
                .cusEmail(dto.getCusEmail())
                .cusPhone(dto.getCusPhone())
                .cusAddr(dto.getCusAddr())
                .build();
        Customer saved = repository.save(c);
        return modelMapper.map(saved, CustomerResponseDto.class);
    }

    @Override
    @Transactional
    public CustomerResponseDto update(Long id, CustomerRequestDto dto) {
        Customer existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (dto.getCusComp() != null) existing.changeCusComp(dto.getCusComp());
        if (dto.getCusName() != null) existing.changeCusName(dto.getCusName());
        if (dto.getCusEmail() != null) existing.changeCusEmail(dto.getCusEmail());
        if (dto.getCusPhone() != null) existing.changeCusPhone(dto.getCusPhone());
        if (dto.getCusAddr() != null) existing.changeCusAddr(dto.getCusAddr());

        return modelMapper.map(existing, CustomerResponseDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
