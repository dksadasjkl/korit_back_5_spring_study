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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class StudentController {

    @PostMapping("/student")
    // RequestBody 요청 때 JSON 데이터로
    // required = false 값이 null 이 아니여도 됨.
    public ResponseEntity<?> addStudent(@CookieValue(required = false) String students, @RequestBody Student student) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper objectMapper = new ObjectMapper(); // GSON 과 동일
        List<Student> studentList = new ArrayList<>();
        int lastId = 0;

        System.out.println(students);

        if(students != null) {
            if(!students.isBlank()) {
                for(Object object : objectMapper.readValue(students, List.class)) { // 배열 변환 -> 오브젝트객체로 들어감
                    Map<String, Object> studentMap = (Map<String, Object>) object; // Map 다운캐스팅
                    studentList.add(objectMapper.convertValue(studentMap, Student.class)); // Student 객체 변환
                }
                lastId = studentList.get(studentList.size() - 1).getStudentId();
            }
        }

        student.setStudentId(lastId + 1); //JSON(name) + id
        studentList.add(student);
        System.out.println(studentList);
        
        String studentListJson = objectMapper.writeValueAsString(studentList); // toJson 과 동일
        System.out.println(studentListJson);
        
        ResponseCookie responseCookie = ResponseCookie
                .from("students", URLEncoder.encode(studentListJson, "UTF-8")) // 인코딩
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60 * 5)
                .build();

        // (")문자 저장x

        return ResponseEntity
                .created(null)
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

        findStudent = optionalStudent.get();

//        if(findStudent == null) {
//            return ResponseEntity.badRequest().body(Map.of("errorMessege", "존재하지 않는 10입니다."));
//        }
        return  ResponseEntity.ok().body(findStudent);
    }
}
