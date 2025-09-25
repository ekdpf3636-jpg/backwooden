package com.springboot.wooden.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ITEM_TBL")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    private Long id;   // PK 자동증가

    @Column(name = "item_code", nullable = false, length = 20)
    private String code;  // 상품코드

    @Column(name = "item_name", nullable = false, length = 30)
    private String name;  // 상품명

    @Column(name = "item_spec", nullable = false, length = 40)
    private String spec;  // 규격

    @Column(name = "item_price", nullable = false)
    private int price; // 단가
}
