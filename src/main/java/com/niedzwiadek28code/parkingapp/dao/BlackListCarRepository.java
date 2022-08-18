package com.niedzwiadek28code.parkingapp.dao;

import com.niedzwiadek28code.parkingapp.entity.BlackListCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListCarRepository extends JpaRepository<BlackListCar, String> {
    BlackListCar findBlackListCarByRegistrationNumberContainingIgnoreCase(String registrationNumber);
    boolean existsByRegistrationNumberContainingIgnoreCase(String registrationNumber);
}
