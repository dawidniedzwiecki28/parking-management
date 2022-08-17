package com.niedzwiadek28code.parkingapp.dao;

import com.niedzwiadek28code.parkingapp.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findCarByRegistrationNumberContainingIgnoreCase(String registrationNumber);

    List<Car> findCarsByActiveTrue();

    List<Car> findCarsByActiveTrueAndCheckerFalse();

    boolean existsByRegistrationNumberContainingIgnoreCase(String registrationNumber);

    boolean existsByRegistrationNumberContainingIgnoreCaseAndBlackListTrue(String registrationNumber);
}
