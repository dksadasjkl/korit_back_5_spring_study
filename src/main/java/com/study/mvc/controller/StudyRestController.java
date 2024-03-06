package com.study.mvc.controller;

import com.study.mvc.entity.Student;
import com.study.mvc.service.StudentService;
import com.study.mvc.service.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudyRestController {

    @Autowired
    @Qualifier("studentServiceImpl")
    private StudentService studentService;

    @GetMapping("/ex/students")
    public ResponseEntity<?> Students() {
        return ResponseEntity.ok(studentService.getStudentList());
    }

    @GetMapping("/ex/students/{index}")
    public ResponseEntity<?> student(@PathVariable int index) {
        return ResponseEntity.ok(studentService.getStudentList(index));
    }

}
