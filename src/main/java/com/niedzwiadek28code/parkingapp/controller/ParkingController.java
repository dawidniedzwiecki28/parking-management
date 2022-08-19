package com.niedzwiadek28code.parkingapp.controller;

import com.niedzwiadek28code.parkingapp.entity.Car;
import com.niedzwiadek28code.parkingapp.service.ParkingServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    private final ParkingServiceImpl carServiceImpl;

    public ParkingController(ParkingServiceImpl carServiceImpl) {
        this.carServiceImpl = carServiceImpl;
    }

    @GetMapping({"/list"})
    public ModelAndView findCars() {
        return carServiceImpl.findCars();
    }

    @GetMapping("/checking")
    public ModelAndView activeCarsForChecking() {
        return carServiceImpl.listCarsForChecking();
    }

    @GetMapping("/addCarForm")
    public ModelAndView addCarForm() {
        return carServiceImpl.addCarForm();
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam String number) {
        return carServiceImpl.showCarUpdateForm(number);
    }

    @GetMapping("/carConfirmation")
    public RedirectView carConfirmation(String number) {
        return (carServiceImpl.confirmCar(number));
    }

    @GetMapping("/blackListNotify")
    public ModelAndView showBlackListNotify() {
        return carServiceImpl.showBlackListNotify();
    }

    @GetMapping("/addToBlackList")
    public RedirectView addToBlackLIst(@RequestParam String number) {
        return (carServiceImpl.addCarToBlackList(number));
    }

    @GetMapping("/checkerCleaner")
    public RedirectView checkerCleaner() {
        return (carServiceImpl.checkerCleaner());
    }

    @GetMapping("/deactivationCarForCheck")
    public RedirectView deleteCar(String number) {
        return (carServiceImpl.deleteCar(number));
    }

    @PostMapping("/saveCar")
    public RedirectView showBlackListCar(@ModelAttribute Car sourceCar) {
        return (carServiceImpl.addCar(sourceCar));
    }

    @PostMapping("/editCar")
    public RedirectView createOrEditCar(@RequestParam String number, @ModelAttribute Car sourceCar) {
        return (carServiceImpl.editCar(number, sourceCar));
    }

}
