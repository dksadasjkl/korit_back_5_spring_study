package com.study.mvc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.dto.StudentExDto;
import com.study.mvc.entity.Student;
import com.study.mvc.repository.StudentRepository;
import com.study.mvc.repository.StudentRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private final StudentRepository studentRepository;

    @Override
    public List<?> getStudentList() {
        List<String> studentList = studentRepository.getStudentListAll();
        List<Map<String, String>> studentMapList = new ArrayList<>();
        for (String studentName : studentList) {
            studentMapList.add(Map.of("name", studentName));
        }
        return studentMapList;
    }

    @Override
    public Map<String, String> getStudentList(int index) {
        String studentName = studentRepository.findStudentNameByIndex(index);
        return Map.of("name", studentName);
    }
}
