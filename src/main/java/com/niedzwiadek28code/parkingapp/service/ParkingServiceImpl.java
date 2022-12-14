package com.niedzwiadek28code.parkingapp.service;

import com.niedzwiadek28code.parkingapp.dao.BlacklistCarRepository;
import com.niedzwiadek28code.parkingapp.dao.CarRepository;
import com.niedzwiadek28code.parkingapp.entity.BlacklistCar;
import com.niedzwiadek28code.parkingapp.entity.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ParkingServiceImpl implements ParkingService {
    private final CarRepository repository;
    private final BlacklistCarRepository blackListCarRepository;

    public ParkingServiceImpl(final CarRepository repository, final BlacklistCarRepository blackListCarRepository) {
        this.repository = repository;
        this.blackListCarRepository = blackListCarRepository;
    }

    @Override
    public RedirectView editCar(String number, Car sourceCar) {
        Car car = repository.findCarByRegistrationNumberContainingIgnoreCase(number);
        if (!repository.existsByRegistrationNumberContainingIgnoreCase(sourceCar.getRegistrationNumber())) {
            repository.save(sourceCar);
            repository.delete(car);
        } else {
            Car toUpdateCar = repository.findCarByRegistrationNumberContainingIgnoreCase(sourceCar.getRegistrationNumber());
            toUpdateCar.toUpdate(sourceCar);
            repository.save(toUpdateCar);
        }
        return new RedirectView("list");
    }

    @Override
    public RedirectView addCar(Car sourceCar) {
        if (blackListCarRepository.existsByRegistrationNumberContainingIgnoreCase(sourceCar.getRegistrationNumber())) {
            blackListCarRepository.delete(blackListCarRepository.findBlacklistCarByRegistrationNumberContainingIgnoreCase(sourceCar.getRegistrationNumber()));
            repository.save(sourceCar);
            return new RedirectView("blackListNotify");
        }

        if (repository.existsByRegistrationNumberContainingIgnoreCase(sourceCar.getRegistrationNumber())) {
            log.info("That car already exist") ;
        } else {
            repository.save(sourceCar);
        }

        return new RedirectView("list");
    }

    @Override
    public RedirectView deleteCar(String number) {
        Car car = repository.findCarByRegistrationNumberContainingIgnoreCase(number);
        LocalDateTime dateTime = LocalDateTime.now();
        car.setDepartureDate(dateTime);
        repository.delete(car);

        return new RedirectView("checking");
    }

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public RedirectView confirmCar(String number) {
        Car car = repository.findCarByRegistrationNumberContainingIgnoreCase(number);
        car.setChecker(true);
        repository.save(car);

        return new RedirectView("checking");
    }

    @Override
    public ModelAndView showCarUpdateForm(String number) {
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
    public ModelAndView findCars() {
        ModelAndView mav = new ModelAndView("list-cars");
        CarsPaidTimeChecker checker = new CarsPaidTimeChecker(repository);
        List<Car> list = repository.findAll();
        checker.checkCarsPaidTime(list);
        mav.addObject("cars", list);

        return mav;
    }

    @Override
    public ModelAndView listCarsForChecking() {
        ModelAndView mav = new ModelAndView("list-cars-for-check");
        List<Car> carsForCheck = repository.findCarsByCheckerFalse();
        mav.addObject("cars", carsForCheck);

        return mav;
    }

    @Override
    public RedirectView addCarToBlackList(String number) {
        BlacklistCar blackListCar = new BlacklistCar(number);
        blackListCarRepository.save(blackListCar);

        Car car = repository.findCarByRegistrationNumberContainingIgnoreCase(number);
        repository.delete(car);

        return new RedirectView("checking");
    }

    @Override
    public RedirectView checkerCleaner() {
        List<Car> list = repository.findAll();
        for (Car car : list) {
            car.setChecker(false);
            repository.save(car);
        }
        return new RedirectView("list");
    }
}
