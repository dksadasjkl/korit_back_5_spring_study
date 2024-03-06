package com.study.mvc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

// Bean 수동 등록 (2개 이상 등록 가능)
// -> 1. 수동불가능한 클래스에 사용
// -> 2. 세팅목적
@Configuration
public class BeanConfig {

    @Bean
    public ObjectMapper ObjectMapper() {
        return new ObjectMapper();
    }

}
