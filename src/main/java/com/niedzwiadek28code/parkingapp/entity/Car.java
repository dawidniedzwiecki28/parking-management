package com.niedzwiadek28code.parkingapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
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

    public void toUpdate(Car sourceCar) {
        this.registrationNumber = sourceCar.registrationNumber;
        this.country = sourceCar.country;
        this.paymentStatus = sourceCar.paymentStatus;
        this.arrivalDate = sourceCar.arrivalDate;
        this.departureDate = sourceCar.departureDate;
    }
}
