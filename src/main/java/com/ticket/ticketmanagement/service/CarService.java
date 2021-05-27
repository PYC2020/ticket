package com.ticket.ticketmanagement.service;

import com.ticket.ticketmanagement.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> selectById(int id);

    int addCar(Car car);

    List<Car> selectAll();


    int deleteById(int id);

    int update(Car car);
}
