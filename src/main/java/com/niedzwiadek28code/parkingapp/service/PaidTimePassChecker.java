package com.niedzwiadek28code.parkingapp.service;

import com.niedzwiadek28code.parkingapp.entity.Car;
import com.niedzwiadek28code.parkingapp.dao.CarRepository;

import java.util.List;

public class PaidTimePassChecker {
    private final CarRepository repository;

    public PaidTimePassChecker(CarRepository repository) {
        this.repository = repository;
    }

    public void checkCarsPaidTime(){
        List<Car> carList = repository.findAll();
        for (Car car : carList) {

            if(car.getDepartureDate()==null){
                continue;
            }
            if(car.timeUpChecker()){
                car.setTimeUp(true);
                repository.save(car);
            }
        }
    }
}
