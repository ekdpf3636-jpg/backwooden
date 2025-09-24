package com.springboot.wooden.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    // 이메일 검증
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_,-]+@(,+)$");

    // 번호 정규식 적용
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[0-9]{10,11}$"); // 01012345678

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

}
