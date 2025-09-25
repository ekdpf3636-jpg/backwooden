package com.springboot.wooden.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PartRequestDto {

    private String partCode;
    private String partName;
    private String partSpec;
    private BigDecimal partPrice;

    private Long buyerNo; // 등록/수정 시 FK로 받음
}
