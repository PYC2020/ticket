package com.ticket.ticketmanagement.service.impl;

import com.ticket.ticketmanagement.entity.Car;
import com.ticket.ticketmanagement.mapper.impl.CarMapper;
import com.ticket.ticketmanagement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: ticket-management
 * @description:
 * @author: Joe
 * @create: 2021-05-27 12:26
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarMapper carMapper;


    @Override
    public List<Car> selectById(int id) {
        Example example=new Example(Car.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("id" ,id);
        List<Car> list=carMapper.selectByExample(example);
        return list;
    }

    @Override
    public int addCar(Car car) {
        if (car.getStartAddress() != null && car.getEndAddress() != null && car.getStartTime() !=null &&
            car.getEndTime() !=null &&car.getCarNumber() !=null && car.getStatus()!=null) {
            return carMapper.insert(car);
        }
        return 0;
    }

    @Override
    public List<Car> selectAll() {
        return carMapper.selectAll();
    }

    @Override
    public int deleteById(int id) {
        return carMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Car car) {
        return carMapper.updateByPrimaryKeySelective(car);
    }
}
