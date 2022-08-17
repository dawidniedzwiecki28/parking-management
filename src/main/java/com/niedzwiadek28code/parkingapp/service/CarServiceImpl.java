package com.niedzwiadek28code.parkingapp.service;

import com.niedzwiadek28code.parkingapp.dao.CarRepository;
import com.niedzwiadek28code.parkingapp.entity.Car;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CarServiceImpl implements CarService {
    private final CarRepository repository;

    public CarServiceImpl(final CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateExistCarForCreate(Car sourceCar) {
        Car toUpdateCar = repository
                .findCarByRegistrationNumberContainingIgnoreCase(
                        sourceCar.getRegistrationNumber());
        boolean blackList = toUpdateCar.isBlackList();
        toUpdateCar.toUpdate(sourceCar);
        toUpdateCar.setBlackList(blackList);
        repository.save(toUpdateCar);
    }

    @Override
    public void updateExistCarForEdit(Car sourceCar) {
        Car toUpdateCar = repository
                .findCarByRegistrationNumberContainingIgnoreCase(
                        sourceCar.getRegistrationNumber());
        toUpdateCar.toUpdate(sourceCar);
        toUpdateCar.setActive(sourceCar.isActive());
        repository.save(toUpdateCar);
    }

    @Override
    public void createOrUpdateCar(Car sourceCar) {
        if (!repository.existsByRegistrationNumberContainingIgnoreCase(sourceCar.getRegistrationNumber())) {
            repository.save(sourceCar);
        } else {
            updateExistCarForCreate(sourceCar);
        }
    }

    @Override
    public RedirectView createOrEditCar(String number, Car sourceCar) {
        Car car = repository.findCarByRegistrationNumberContainingIgnoreCase(number);
        if (!repository.existsByRegistrationNumberContainingIgnoreCase(sourceCar.getRegistrationNumber())) {
            repository.save(sourceCar);
            repository.delete(car);
        } else {
            updateExistCarForEdit(sourceCar);
        }
        return new RedirectView("list");
    }

    @Override
    public RedirectView showBlackListCar(Car sourceCar) {
        if (repository.existsByRegistrationNumberContainingIgnoreCaseAndBlackListTrue(sourceCar.getRegistrationNumber())) {
            createOrUpdateCar(sourceCar);
            return new RedirectView("blackListNotify");
        }
        createOrUpdateCar(sourceCar);

        return new RedirectView("list");
    }

    @Override
    public RedirectView carDeactivation(String number) {
        Car car = repository.findCarByRegistrationNumberContainingIgnoreCase(number);
        LocalDateTime dateTime = LocalDateTime.now();
        car.setDepartureDate(dateTime);
        car.setChecker(false);
        car.setActive(false);
        repository.save(car);

        return new RedirectView("list");
    }

    @Override
    public RedirectView carDeactivationForCheck(String number) {
        Car car = repository.findCarByRegistrationNumberContainingIgnoreCase(number);
        LocalDateTime dateTime = LocalDateTime.now();
        car.setDepartureDate(dateTime);
        car.setChecker(false);
        car.setActive(false);
        repository.save(car);

        return new RedirectView("checking");
    }

    @Override
    public RedirectView carConfirmation(String number) {
        Car car = repository.findCarByRegistrationNumberContainingIgnoreCase(number);
        car.setChecker(true);
        repository.save(car);

        return new RedirectView("checking");
    }

    @Override
    public ModelAndView showUpdateForm(String number) {
        ModelAndView mav = new ModelAndView("update-car-form");
        Car car = repository.findCarByRegistrationNumberContainingIgnoreCase(number);
        mav.addObject("car", car);

        return mav;
    }

    @Override
    public ModelAndView addCarForm() {
        ModelAndView mav = new ModelAndView("add-car-form");
        Car newCar = new Car();
        mav.addObject("car", newCar);

        return mav;
    }

    @Override
    public ModelAndView showBlackListNotify() {
        ModelAndView mav = new ModelAndView("black-list-notify");
        Car newCar = new Car();
        mav.addObject("car", newCar);

        return mav;
    }

    @Override
    public ModelAndView findActiveCars() {
        ModelAndView mav = new ModelAndView("list-cars");
        PaidTimePassChecker checker = new PaidTimePassChecker(repository);
        List<Car> list = repository.findCarsByActiveTrue();
        for (Car car : list) {
            car.setChecker(false);
            repository.save(car);
        }
        mav.addObject("cars", list);
        checker.checkCarsPaidTime();

        return mav;
    }

    @Override
    public ModelAndView activeCarsForChecking() {
        ModelAndView mav = new ModelAndView("list-cars-for-check");
        List<Car> carsForCheck = repository.findCarsByActiveTrueAndCheckerFalse();
        mav.addObject("cars", carsForCheck);

        return mav;
    }
}
