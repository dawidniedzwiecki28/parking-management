package com.niedzwiadek28code.parkingapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table
public class Car {
    @Id
    private String registrationNumber;
    private String country;
    private boolean paymentStatus;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private boolean timeUp;
    private boolean checker;

    public Car() {
    }

    public Car(String registrationNumber,
               String country,
               LocalDateTime arrivalDate,
               boolean paymentStatus,
               LocalDateTime departureDate) {
        this.registrationNumber = registrationNumber.toUpperCase();
        this.country = country;
        this.paymentStatus = paymentStatus;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.checker = false;
    }

    public Car(String registrationNumber,
               LocalDateTime arrivalDate,
               boolean paymentStatus) {
        this.registrationNumber = registrationNumber;
        this.paymentStatus = paymentStatus;
        this.arrivalDate = arrivalDate;
        this.checker = false;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getCountry() {
        return country;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public boolean isTimeUp() {
        return timeUp;
    }

    public void setTimeUp(boolean timeUp) {
        this.timeUp = timeUp;
    }

    public void setChecker(boolean checker) {
        this.checker = checker;
    }

    public void toUpdate(Car sourceCar) {
        this.registrationNumber = sourceCar.registrationNumber;
        this.country = sourceCar.country;
        this.paymentStatus = sourceCar.paymentStatus;
        this.arrivalDate = sourceCar.arrivalDate;
        this.departureDate = sourceCar.departureDate;
    }

    @JsonIgnore
    public boolean timeUpChecker(LocalDateTime now) {
        if (this.departureDate == null) {
            return false;
        }
        Duration duration = Duration.between(now, this.departureDate);
        return (duration.isNegative());
    }
}
