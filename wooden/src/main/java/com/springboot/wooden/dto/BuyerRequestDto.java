package com.springboot.wooden.dto;// BuyerRequestDto.java
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerRequestDto {

    private String buyerComp;
    private String buyerName;
    private String buyerEmail;
    private String buyerPhone;
    private String buyerAddr;
}
