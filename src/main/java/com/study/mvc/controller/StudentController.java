package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.dto.StudentReqDto;
import com.study.mvc.entity.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class StudentController {

    @PostMapping("/student")
    // RequestBody 요청 때 JSON 데이터로
    public ResponseEntity<?> addStudent(@CookieValue(required = false) String students, @RequestBody Student student) throws JsonProcessingException {
        List<Student> studentList = new ArrayList<>();
        int lastId = 0;

        if(students != null) {
            if (!students.isBlank()) {
                ObjectMapper studentsCookie = new ObjectMapper();
                studentList = studentsCookie.readValue(students, List.class);
                lastId = studentList.get(studentList.size() - 1).getStudentId();
            }
        }

        student.setStudentId(lastId + 1);
        studentList.add(student);

        ObjectMapper newStudentList = new ObjectMapper();
        String newStudents = newStudentList.writeValueAsString(studentList);


        ResponseCookie responseCookie = ResponseCookie
                .from("test", "test") // 쿠키의 이름과 값 설정
                .httpOnly(true) // HTTP 전용 설정
                .secure(true) // 보안 전용 설정
                .path("/") // 쿠키의 유효 경로 설정
                .maxAge(60) // 쿠키의 만료 시간 설정 (60초)
                .build();
        return ResponseEntity.created(null)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(student);

    }

    @GetMapping("/student")
    public StudentReqDto getStudentInfo(HttpServletResponse httpServletResponse) {
        StudentReqDto studentReqDto = StudentReqDto.builder()
                .name("서창현")
                .age(27)
                .phone("010-4874-1111")
                .address("부산 진구")
                .build();
        httpServletResponse.setStatus(404);
        return studentReqDto;
    }

    @GetMapping("/student2")
    public ResponseEntity<?> getStudentInfo2(StudentReqDto studentReqDto) {
        System.out.println(studentReqDto);
        return ResponseEntity.badRequest().header("test", "header_test").body(studentReqDto.toRespDtoDto());
    }

    // 주소값을 가져옴
    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable("studentId") int studentId) {
        List<Student> studentList = List.of(
            new Student(1, "홍춘일"),
            new Student(2, "홍춘이"),
            new Student(3, "홍춘삼"),
            new Student(4, "홍춘사")
        );

//        int findStudent = studentId - 1;
//        if (findStudent > studentList.size()) {
//            return ResponseEntity.badRequest().body("존재하지 않는 10입니다");
//        }
//        return ResponseEntity.ok().body(studentList.get(findStudent));

        Student findStudent = null;
        for(Student student : studentList) {
            if(student.getStudentId() == studentId) {
                findStudent = student;
            }
        }

        Optional<Student> optionalStudent =
                studentList.stream()
                        .filter(student -> student.getStudentId() == studentId).findFirst();
        if(optionalStudent.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", "존재하지 않는 10입니다."));
        }

        findStudent =optionalStudent.get();

//        if(findStudent == null) {
//            return ResponseEntity.badRequest().body(Map.of("errorMessege", "존재하지 않는 10입니다."));
//        }
        return  ResponseEntity.ok().body(findStudent);
    }
}
