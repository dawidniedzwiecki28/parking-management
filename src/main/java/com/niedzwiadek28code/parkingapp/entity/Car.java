package com.niedzwiadek28code.parkingapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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
    }

    public Car(String registrationNumber,
               String country,
               LocalDateTime arrivalDate,
               boolean paymentStatus) {
        this.registrationNumber = registrationNumber.toUpperCase();
        this.country = country;
        this.paymentStatus = paymentStatus;
        this.arrivalDate = arrivalDate;
    }

    public Car(String registrationNumber,
               boolean paymentStatus,
               LocalDateTime arrivalDate) {
        this.registrationNumber = registrationNumber;
        this.paymentStatus = paymentStatus;
        this.arrivalDate = arrivalDate;
    }

    public Car() {
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber.toUpperCase();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
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

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isTimeUp() {
        return timeUp;
    }

    public void setTimeUp(boolean timeUp) {
        this.timeUp = timeUp;
    }

    public boolean isChecker() {
        return checker;
    }

    public void setChecker(boolean checker) {
        this.checker = checker;
    }

    @Override
    public String toString() {
        return "Car{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", country='" + country + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", timeUp=" + timeUp +
                ", checker=" + checker +
                '}';
    }

    public void toUpdate(Car sourceCar) {
        this.registrationNumber = sourceCar.registrationNumber;
        this.country = sourceCar.country;
        this.paymentStatus = sourceCar.paymentStatus;
        this.arrivalDate = sourceCar.arrivalDate;
        this.departureDate = sourceCar.departureDate;
    }

    @JsonIgnore
    public boolean timeUpChecker() {
        Duration duration = Duration.between(LocalDateTime.now(), this.departureDate);
        return (duration.isNegative());
    }
}
