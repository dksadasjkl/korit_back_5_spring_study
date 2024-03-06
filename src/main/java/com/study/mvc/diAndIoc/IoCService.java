package com.study.mvc.diAndIoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component  // 객체 생성
            // 1. @Component + @Autowired 세트
            // 2. @Component 끼리만 소통
@RequiredArgsConstructor
public class IoCService {

    @Autowired
    private  IoCRepository ioCRepository;

    public String getJson() throws JsonProcessingException {
        Map<String, String> nameMap = ioCRepository.converNameMap();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(nameMap);
    }
}
