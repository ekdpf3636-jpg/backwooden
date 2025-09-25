package com.springboot.wooden.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/* Buyer_no (Auto increment)
 - PK: buyer_no (Auto_increment)
 - Not null: comp/name/email/phone/addr 전부 cot null(명세반영)
 - 길이는 DB 명세 (20,10,40,11,50)에 맞춤
*/

@Entity
@Table(name = "BUYER_TBL")
@Getter
@Setter
public class Buyer {

    // PK: AUTO_INCREMENT -> IDENTITY 전략
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyerNo; // DB: buyer_no

    // 구매처명 (varchar(20) not null)
    @Column(nullable = false, length =20)
    private String buyerComp; // DB: buyer_comp

    //담당자명 (varchar(10) not null)
    @Column(nullable = false, length =10)
    private String buyerName; // DB: buyer_name

    // 이메일 (varchar(40) not null)
    // - @ 형식 검사는 컨드롤러/서비스/DTO 레벨에서 한번 더 해줄 수 있음
    @Column(nullable = false, length =40)
    private String buyerEmail; // DB: buyer_email

    // 전화번호 ( varchar(11) not null)
    @Column(nullable = false, length =11)
    private String buyerPhone; // DB: buyer_phone

    // 주소 (varchar(50) not null)
    @Column(nullable = false, length =50)
    private String buyerAddr; // DB: buyer_addr
}
