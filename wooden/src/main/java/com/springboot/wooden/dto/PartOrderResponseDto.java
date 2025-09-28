package com.springboot.wooden.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartOrderResponseDto {
    private Long poNo;
    private Long buyerNo;
    private String buyerComp;   // Buyer 이름
    private Long partNo;
    private String partName;    // Part 이름
    private int poQty;
    private int poPrice;
    private String poState;
    private LocalDate poDate;
    private String buyerAddr;
}
