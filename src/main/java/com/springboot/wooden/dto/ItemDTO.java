package com.springboot.wooden.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ItemDTO {
    // DB 테이블과 동일한 필드명, snake_case 대신 camelCase 사용
    private Long itemNo;
    private String itemCode;
    private String itemName;
    private String itemSpec;
    private BigDecimal itemPrice;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
