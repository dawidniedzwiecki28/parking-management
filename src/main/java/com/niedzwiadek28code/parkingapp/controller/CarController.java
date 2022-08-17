package com.niedzwiadek28code.parkingapp.controller;

import com.niedzwiadek28code.parkingapp.entity.Car;
import com.niedzwiadek28code.parkingapp.service.CarServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/parking")
public class CarController {
    private final CarServiceImpl carServiceImpl;

    public CarController(CarServiceImpl carServiceImpl) {
        this.carServiceImpl = carServiceImpl;
    }

    @GetMapping({"/list"})
    public ModelAndView findActiveCars() {
        return carServiceImpl.findActiveCars();
    }

    @GetMapping("/checking")
    public ModelAndView activeCarsForChecking() {
        return carServiceImpl.activeCarsForChecking();
    }

    @GetMapping("/addCarForm")
    public ModelAndView addCarForm() {
        return carServiceImpl.addCarForm();
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam String number) {
        return carServiceImpl.showUpdateForm(number);
    }

    @GetMapping("/deactivationCar")
    public RedirectView carDeactivation(String number) {
        return (carServiceImpl.carDeactivation(number));
    }

    @GetMapping("/deactivationCarForCheck")
    public RedirectView carDeactivationForCheck(String number) {
        return (carServiceImpl.carDeactivationForCheck(number));
    }

    @GetMapping("/carConfirmation")
    public RedirectView carConfirmation(String number) {
        return (carServiceImpl.carConfirmation(number));
    }

    @GetMapping("/blackListNotify")
    public ModelAndView showBlackListNotify() {
        return carServiceImpl.showBlackListNotify();
    }

    @PostMapping("/saveCar")
    public RedirectView showBlackListCar(@ModelAttribute Car sourceCar) {
        return (carServiceImpl.showBlackListCar(sourceCar));
    }

    @PostMapping("/editCar")
    public RedirectView createOrEditCar(@RequestParam String number, @ModelAttribute Car sourceCar) {
        return (carServiceImpl.createOrEditCar(number, sourceCar));
    }

    @PutMapping("/saveCar")
    public void updateExistCar(Car sourceCar) {
        carServiceImpl.updateExistCarForCreate(sourceCar);
    }

    @PutMapping("/editCar")
    public void editExistCar(Car sourceCar) {
        carServiceImpl.updateExistCarForCreate(sourceCar);
    }

}
