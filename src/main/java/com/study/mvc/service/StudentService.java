package com.study.mvc.service;

import com.study.mvc.entity.Student;

import java.util.List;

public interface StudentService {
    public List<?> getStudentList();
    public Object getStudentList(int index);

}
