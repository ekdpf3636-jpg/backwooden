package com.springboot.wooden.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerRequestDto {
    @NotBlank(message = "거래처명은 필수입니다")
    private String company;

    private String manager;

    @Email(message = "이메일 형식이 올바르지 않습니다")
    private String email;

    private String phone;
    private String address;

}
