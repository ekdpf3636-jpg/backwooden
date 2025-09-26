// src/main/java/com/springboot/wooden/domain/Buyer.java
package com.springboot.wooden.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BUYER_TBL")
@Getter @Setter
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  @Column(name = "buyer_no")
    private Long buyerNo; // PK: AUTO_INCREMENT

    @Column(name = "buyer_comp", nullable = false, length = 20)
    private String buyerComp;   // 구매처명

    @Column(name = "buyer_name", nullable = false, length = 10)
    private String buyerName;   // 담당자

    @Column(name = "buyer_email", nullable = false, length = 40)
    private String buyerEmail;  // 이메일

    @Column(name = "buyer_phone", nullable = false, length = 11)
    private String buyerPhone;  // 전화(숫자만 10~11자리)

    @Column(name = "buyer_addr", nullable = false, length = 50)
    private String buyerAddr;   // 주소
}
