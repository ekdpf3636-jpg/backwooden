package com.springboot.wooden.dto;

import lombok.Getter;
import lombok.Setter;

//응답 DTO
@Getter
@Setter
public class BuyerResponseDto {

    private Long buyerNo;
    private String buyerComp;
    private String buyerName;
    private String buyerEmail;
    private String buyerPhone;
    private String buyerAddr;

}
