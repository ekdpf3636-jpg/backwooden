package com.springboot.wooden.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 전역 CORS 설정 (Spring Security 미사용)
 * - 개발용: CRA(3000), Vite(5173) 허용
 * - allowCredentials(true) 쓰면 allowedOrigins에 "*" 못 씀
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry reg) {
        reg.addMapping("/api/**")                 // API 경로에만 CORS 허용
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://127.0.0.1:3000",
                        "http://localhost:5173",
                        "http://127.0.0.1:5173"
                )
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Location")           // 필요시 노출할 응답 헤더
                .allowCredentials(true)               // 쿠키/인증정보 포함 허용(필요 없으면 false)
                .maxAge(3600);                        // preflight 캐시(초)
    }
}
