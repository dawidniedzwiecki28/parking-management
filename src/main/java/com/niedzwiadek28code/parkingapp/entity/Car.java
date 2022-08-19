package com.niedzwiadek28code.parkingapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
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

    public Car(String registrationNumber, String country, LocalDateTime arrivalDate, boolean paymentStatus, LocalDateTime departureDate) {
        this.registrationNumber = registrationNumber;
        this.country = country;
        this.arrivalDate = arrivalDate;
        this.paymentStatus = paymentStatus;
        this.departureDate = departureDate;
    }

    public Car(String registrationNumber, LocalDateTime arrivalDate) {
        this.registrationNumber = registrationNumber;
        this.arrivalDate = arrivalDate;
    }

    public void toUpdate(Car sourceCar) {
        this.registrationNumber = sourceCar.registrationNumber;
        this.country = sourceCar.country;
        this.paymentStatus = sourceCar.paymentStatus;
        this.arrivalDate = sourceCar.arrivalDate;
        this.departureDate = sourceCar.departureDate;
    }
}
