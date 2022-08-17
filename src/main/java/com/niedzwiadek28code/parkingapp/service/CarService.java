package com.niedzwiadek28code.parkingapp.service;

import com.niedzwiadek28code.parkingapp.entity.Car;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public interface CarService {
    void updateExistCarForCreate(Car sourceCar);

    void updateExistCarForEdit(Car sourceCar);

    void createOrUpdateCar(Car sourceCar);

    RedirectView carConfirmation(String number);

    RedirectView showBlackListCar(@ModelAttribute Car sourceCar);

    RedirectView createOrEditCar(@RequestParam String number, @ModelAttribute Car sourceCar);
    RedirectView carDeactivation(@RequestParam String number);

    RedirectView carDeactivationForCheck(@RequestParam String number);

    ModelAndView showUpdateForm(@RequestParam String number);

    ModelAndView addCarForm();

    ModelAndView showBlackListNotify();

    ModelAndView findActiveCars();

    ModelAndView activeCarsForChecking();
}
