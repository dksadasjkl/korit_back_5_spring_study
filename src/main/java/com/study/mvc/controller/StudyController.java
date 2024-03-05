package com.study.mvc.controller;

import com.study.mvc.model.HelloModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


// F5 파일 카피
// ctrl + y 드래그 삭제
// ctrl + d 드래그 복사
// ctrl + Alt + L 코드정렬
// shift f6 파일명 변경
// Alt Shift 방향키 > 이동

@Controller // 서블릿 파일
public class StudyController {

    // MVC
    @GetMapping("/hello")
    public String helloPage(Model model) {
//        Map<String, Object> model = new HashMap<>();
//        model.put("name1", "서창현1");
//        model.put("name2", "서창현2");
//        model.put("name3", "서창현3");
        HelloModel helloModel = HelloModel.builder()
                .name1("홍길일")
                .name2("홍길이")
                .name3("홍길삼")
                .build();
        model.addAttribute("h", helloModel);
        return "hello"; // 파일명 -> thymeleaf
    }

    // * REST
    @GetMapping("/test") // Get 요청으로 매핑
    @ResponseBody // *** 데이터 응답  (view 응답 x) ***
    public Map<String, Object> testPage() {
        Map<String, Object> testObj = new HashMap<>();
        testObj.put("age", 27); //JSON 데이터로 자동변환
        return testObj;
    }
}
