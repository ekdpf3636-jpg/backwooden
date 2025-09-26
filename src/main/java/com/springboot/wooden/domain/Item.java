package com.springboot.wooden.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_tbl")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    private Long itemNo;

    @Column( name = "item_code", nullable = false, length = 20)
    private String itemCode;

    @Column(name = "item_name", nullable = false, length = 30)
    private String itemName;

    @Column(name = "item_spec", nullable = false, length = 40)
    private String itemSpec;

    @Column(name = "item_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal itemPrice;

    public void changeItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    public void changeItemName(String itemName) {
        this.itemName = itemName;
    }
    public void changeItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }
    public void changeItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
}
