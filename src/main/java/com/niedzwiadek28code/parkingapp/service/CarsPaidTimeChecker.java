package com.niedzwiadek28code.parkingapp.service;

import com.niedzwiadek28code.parkingapp.dao.CarRepository;
import com.niedzwiadek28code.parkingapp.entity.Car;
import com.niedzwiadek28code.parkingapp.utils.TimeUpChecker;

import java.util.List;

public class CarsPaidTimeChecker {
    private final CarRepository repository;

    public CarsPaidTimeChecker(CarRepository repository) {
        this.repository = repository;
    }

    TimeUpChecker checker = new TimeUpChecker();

    public void checkCarsPaidTime(List<Car> carList) {
        for (Car car : carList) {
            if (checker.timeUpChecker(car.getDepartureDate())) {
                car.setTimeUp(true);
                repository.save(car);
            }
        }
    }
}
