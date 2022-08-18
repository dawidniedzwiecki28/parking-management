package com.niedzwiadek28code.parkingapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class BlacklistCar {
    @Id
    private String registrationNumber;

    public BlacklistCar(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public BlacklistCar() {
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

}
