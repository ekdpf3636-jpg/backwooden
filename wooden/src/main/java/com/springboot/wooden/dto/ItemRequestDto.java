package com.springboot.wooden.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {

    @NotBlank(message = "상품코드는 필수입니다")
    private String code;

    @NotBlank(message = "상품명은 필수입니다")
    private String name;

    @NotBlank(message = "상품규격은 필수입니다")
    private String spec;

    @NotNull(message = "상품단가는 필수입니다")
    private int price;
}
