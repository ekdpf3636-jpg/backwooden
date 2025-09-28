package com.springboot.wooden.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {
    private Long itemNo;
    private String itemCode;
    private String itemName;
    private String itemSpec;
    private int itemPrice;
}
