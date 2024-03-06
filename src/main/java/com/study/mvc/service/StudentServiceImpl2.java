package com.study.mvc.service;

import com.study.mvc.dto.StudentExDto;
import com.study.mvc.entity.Student;
import com.study.mvc.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl2 implements StudentService{

    @Autowired
    private final StudentRepository studentRepository;

    @Override
    public List<?> getStudentList() {
        List<String> studentList = studentRepository.getStudentListAll();
        List<StudentExDto> studentMapList = new ArrayList<>();
        for (String studentName : studentList) {
            studentMapList.add(new StudentExDto(studentName));
        }
        return studentMapList;
    }

    @Override
    public Object getStudentList(int index) {
        String studentName = studentRepository.findStudentNameByIndex(index);
        return new StudentExDto(studentName);
    }
}
