package com.springboot.wooden.service;

import com.springboot.wooden.domain.Customer;
import com.springboot.wooden.domain.Item;
import com.springboot.wooden.domain.Order;
import com.springboot.wooden.dto.OrderRequestDto;
import com.springboot.wooden.dto.OrderResponseDto;
import com.springboot.wooden.repository.CustomerRepository;
import com.springboot.wooden.repository.ItemRepository;
import com.springboot.wooden.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 필드 생성자 자동 주입
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    // 전체 조회
    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderResponseDto.class))
                .toList();
    }

    // 판매처명으로 조회
    @Override
    public Optional<OrderResponseDto> getByCompany(String company) {
        return orderRepository.findByCustomer_Company(company) // Customer 엔티티 내부의 company 필드 기준
                .map(order -> modelMapper.map(order, OrderResponseDto.class));
    }

    // 등록
    @Override
    public OrderResponseDto register(OrderRequestDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("고객 없음: " + dto.getCustomerId()));
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("상품 없음: " + dto.getItemId()));

        Order order = Order.builder()
                .customer(customer)
                .item(item)
                .orderQty(dto.getOrderQty())
                .orderPrice(dto.getOrderPrice())
                .orderState(dto.getOrderState())
                .orderDeliState(dto.getOrderDeliState())
                .orderDate(dto.getOrderDate())
                .cusAddr(dto.getCusAddr())
                .build();

        Order saved = orderRepository.save(order);
        return modelMapper.map(saved, OrderResponseDto.class);
    }

    // 수정
    @Override
    public OrderResponseDto update(Long id, OrderRequestDto dto) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문번호 없음: " + id));

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("고객 없음: " + dto.getCustomerId()));
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("상품 없음: " + dto.getItemId()));

        existing.changeCustomer(customer);
        existing.changeItem(item);
        existing.changeOrderQty(dto.getOrderQty());
        existing.changeOrderPrice(dto.getOrderPrice());
        existing.changeOrderState(dto.getOrderState());
        existing.changeOrderDeliState(dto.getOrderDeliState());
        existing.changeOrderDate(dto.getOrderDate());
        existing.changeCusAddr(dto.getCusAddr());

        Order updated = orderRepository.save(existing);
        return modelMapper.map(updated, OrderResponseDto.class);
    }

    // 삭제
    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
