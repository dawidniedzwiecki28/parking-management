package com.niedzwiadek28code.parkingapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class BlackListCar {
    @Id
    private String registrationNumber;

    public BlackListCar(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public BlackListCar() {
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String toString() {
        return "BlackListCar{" +
                "registrationNumber='" + registrationNumber + '\'' +
                '}';
    }
}
