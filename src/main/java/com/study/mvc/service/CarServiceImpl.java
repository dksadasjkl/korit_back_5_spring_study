package com.study.mvc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.repository.CarRepository;
import com.study.mvc.repository.CarRepositoryImpl;
import com.study.mvc.repository.CarRepositoryImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // @Component 포함
public class CarServiceImpl implements CarService{
    /*
    학생 추가 조회
    Controller
        - StudentRestController
            - Get 요청: /students         - 응답 : [{"name": "전주환"}, {"name": "서창현"}, {"name": "예홍렬"}]
            - Get 요청: /students/0~2     - 응답 : OK{"name": "전주환"}
    Service
        -StudentService(interface)
            - getStudentList()
            - getStudentList(int index)
        -StudentServiceImpl(class)
    Repository
        - StudentRepository(interface)
            - getStudentListAll()
                - ["전주환", "서창현", "예홍렬"]
                - findStudentNameByIndex(int index)
        - StudentRepositoryImpl(class)
            - studentList - ["전주환", "서창현", "예홍렬"]

     */

    private final String componentName = "a";

    @Autowired
    @Qualifier(componentName) // Component 선택(상수o)
    private CarRepository carRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String getCarNames() {
        return String.join(", ", carRepository.getCarName());
    }

    @Override
    public int addCar(String carName) {
        return 0;
    }

}
