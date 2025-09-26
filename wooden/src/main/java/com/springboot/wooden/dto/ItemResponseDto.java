// ItemResponseDto.java
package com.springboot.wooden.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponseDto {
    private Long itemNo;     // PK
    private String itemCode;
    private String itemName;
    private String itemSpec;
    private int itemPrice;
}
