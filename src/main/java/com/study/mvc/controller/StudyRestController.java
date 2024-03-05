package com.study.mvc.controller;

import com.study.mvc.dto.StudentReqDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
controller 명 : StudentController

메소드명: getStudentInfo()
요청 메소드 : Get
요청 URL : /student
요청 Params : name, age, phone, address
응답데이터: JSON(name, age, phone, address)
 */

@RestController // 모든 메소드 ResponseBody 포함
public class StudyRestController {

    @GetMapping("/hello/test")
    // 키값의 값을 n에 넣어라
    // int 자동 파싱
    public String hello(StudentReqDto helloDto) {
        System.out.println(helloDto);
        return "Hello";

    }
}
