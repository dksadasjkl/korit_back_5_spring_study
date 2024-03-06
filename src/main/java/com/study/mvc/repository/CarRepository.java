package com.study.mvc.repository;

import java.util.List;

public interface CarRepository {
    public List<String> getCarName();
    public int insertCar(String carName);
}
