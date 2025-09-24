package com.springboot.wooden.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CUSTOMER_TBL")
@Getter @Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_no")
    private Long id;        // PK 자동증가

    @Column(name = "cus_comp", nullable = false, length = 20)
    private String company;    // 거래처 명

    @Column(name = "cus_name", nullable = false, length = 10)
    private String manager;    // 담당자 명

    @Column(name = "cus_email", nullable = false, length = 40)
    private String email;     // 담당자 이메일

    @Column(name = "cus_phone", nullable = false, length = 11)
    private String phone;     // 회사 번호 (정규식 체크 예정)

    @Column(name = "cus_addr", nullable = false, length = 50)
    private String address;   // 회사 주소
}
