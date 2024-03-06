package com.study.mvc.repository;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarRepositoryImpl2 implements  CarRepository{

    @Override
    public List<String> getCarName() {
        return List.of("K3", "K5");
    }

    @Override
    public int insertCar(String carName) {
        System.out.println("등록된 차량: " + carName);
        return 1;
    }
    
}
