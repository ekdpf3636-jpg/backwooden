package com.springboot.wooden;

import com.springboot.wooden.domain.Customer;
import com.springboot.wooden.dto.CustomerRequestDto;
import com.springboot.wooden.repository.CustomerRepository;
import com.springboot.wooden.service.CustomerServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;   // 가짜 Repository (Mock)

    @InjectMocks
    private CustomerServiceImpl service; // MockService를 주입받은 Controller

    private Customer customer;
    private CustomerRequestDto dto;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 엔티티
        customer = new Customer();
        customer.setId(1L);
        customer.setCompany("성남공장");
        customer.setManager("요미");
        customer.setEmail("test@naver.com");
        customer.setPhone("01012341234");
        customer.setAddress("경기도 우리집");

        //  테스트 데이터 (DTO)
        dto = new CustomerRequestDto();
        dto.setCompany("성남공장");
        dto.setManager("요미");
        dto.setEmail("test@naver.com");
        dto.setPhone("01012341234");
        dto.setAddress("경기도 우리집");
    }


    @Test
    void all_customer() {       // 전체 조회
        // given
        when(repository.findAll()).thenReturn(Arrays.asList(customer));
        // when
        List<Customer> result = service.getAll();
        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCompany()).isEqualTo("성남공장");
    }

    @Test
    void find_company() throws Exception {         // 판매거래처명 조회
        // given
        Mockito.when(service.getByCompany("성남공장"))
                .thenReturn(Optional.of(customer));

        // when
        Optional<Customer> result = service.getByCompany("성남공장");
        
        // then
        assertThat(result).isPresent();
        assertThat(result.get().getManager()).isEqualTo("요미");
    }

    @Test
    void create_success() {         // 등록 성공
        // given
        when(repository.save(any(Customer.class))).thenReturn(customer);
        // when
        Customer saved = service.register(dto);
        // then
        // 1. 호출 여부만 확인
        verify(repository, times(1)).save(any(Customer.class));

        // 2. 실제 저장된 값까지 확인 (ArgumentCaptor)
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(repository).save(captor.capture());   // 저장된 객체 캡처
        Customer captured = captor.getValue();

        assertThat(captured.getCompany()).isEqualTo("성남공장");
        assertThat(captured.getManager()).isEqualTo("요미");
        assertThat(captured.getEmail()).isEqualTo("test@naver.com");
        assertThat(captured.getPhone()).isEqualTo("01012341234");
        assertThat(captured.getAddress()).isEqualTo("경기도 우리집");

        // service.register() 리턴값도 확인
        assertThat(saved).isNotNull();
        assertThat(saved.getCompany()).isEqualTo("성남공장");
    }

    @Test
    void update_success() {
        // given
        when(repository.findById(1L)).thenReturn(Optional.of(customer));
        when(repository.save(any(Customer.class))).thenReturn(customer);

        // when
        dto.setManager("박요미");
        Customer updated = service.update(1L, dto);

        // then
        // 1. 호출 여부만 확인
        verify(repository, times(1)).save(any(Customer.class));

        // 2. 실제 저장된 값까지 확인
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(repository).save(captor.capture());
        Customer captured = captor.getValue();

        assertThat(captured.getManager()).isEqualTo("박요미");

        // 리턴값 확인
        assertThat(updated.getManager()).isEqualTo("박요미");
    }

    @Test
    void delete_success() {
        // given
        doNothing().when(repository).deleteById(1L);

        // when
        service.delete(1L);

        // then
        verify(repository, times(1)).deleteById(1L);
    }
}





















