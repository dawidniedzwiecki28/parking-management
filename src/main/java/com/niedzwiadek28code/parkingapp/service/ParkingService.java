package com.niedzwiadek28code.parkingapp.service;

import com.niedzwiadek28code.parkingapp.entity.Car;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

public interface ParkingService {
    List<Car> findAll();

    RedirectView confirmCar(String number);

    RedirectView addCar(@ModelAttribute Car sourceCar);

    RedirectView editCar(@RequestParam String number, @ModelAttribute Car sourceCar);

    RedirectView deleteCar(@RequestParam String number);

    RedirectView addCarToBlackList(@RequestParam String number);

    RedirectView checkerCleaner();

    ModelAndView showCarUpdateForm(@RequestParam String number);

    ModelAndView addCarForm();

    ModelAndView showBlackListNotify();

    ModelAndView findCars();

    ModelAndView listCarsForChecking();


}
