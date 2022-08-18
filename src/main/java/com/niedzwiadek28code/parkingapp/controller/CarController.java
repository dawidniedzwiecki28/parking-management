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
        return carServiceImpl.findCars();
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

    @GetMapping("/deactivationCarForCheck")
    public RedirectView carDeactivationForCheck(String number) {
        return (carServiceImpl.deleteCar(number));
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
        return (carServiceImpl.addCar(sourceCar));
    }

    @PostMapping("/editCar")
    public RedirectView createOrEditCar(@RequestParam String number, @ModelAttribute Car sourceCar) {
        return (carServiceImpl.editCar(number, sourceCar));
    }
    @GetMapping("/addToBlackList")
    public RedirectView addToBlackLIst(@RequestParam String number){
        return (carServiceImpl.addCarToBlackList(number));
    }
    @GetMapping("/checkerCleaner")
    public RedirectView checkerCleaner(){
        return (carServiceImpl.checkerCleaner());
    }

}
