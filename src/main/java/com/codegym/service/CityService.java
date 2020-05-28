package com.codegym.service;

import com.codegym.model.City;

import java.util.List;

public interface CityService {
    List<City> findAll();
    City findById(Long id);
    void save(City city);
    void remove(Long id);
}
