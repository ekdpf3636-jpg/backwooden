package com.springboot.wooden;

import com.springboot.wooden.controller.CustomerController;
import com.springboot.wooden.domain.Customer;
import com.springboot.wooden.repository.CustomerRepository;
import com.springboot.wooden.service.CustomerService;
import com.springboot.wooden.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;   // 가짜 Repository (Mock)

    @InjectMocks
    private CustomerServiceImpl service; // MockService를 주입받은 Controller

    private Customer customer;

    @BeforeEach
    void setUp() {
        // 테스트 데이터
        customer = new Customer();
        customer.setId(1L);
        customer.setCompany("성남공장");
        customer.setManager("요미");
        customer.setEmail("test@naver.com");
        customer.setPhone("01012341234");
        customer.setAddress("경기도 우리집");
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
        Customer saved = service.register(customer);
        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getCompany()).isEqualTo("성남공장");
        verify(repository, times(1)).save(customer);
    }

    @Test
    void update_success() {
        // given
        when(repository.findById(1L)).thenReturn(Optional.of(customer));
        when(repository.save(any(Customer.class))).thenReturn(customer);

        // when
        customer.setManager("박요미");
        Customer updated = service.update(1L, customer);

        // then
        assertThat(updated.getManager()).isEqualTo("박요미");
        verify(repository, times(1)).save(customer);
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





















