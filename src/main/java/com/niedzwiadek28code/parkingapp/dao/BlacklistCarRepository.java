package com.niedzwiadek28code.parkingapp.dao;

import com.niedzwiadek28code.parkingapp.entity.BlacklistCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistCarRepository extends JpaRepository<BlacklistCar, String> {
    BlacklistCar findBlacklistCarByRegistrationNumberContainingIgnoreCase(String registrationNumber);

    boolean existsByRegistrationNumberContainingIgnoreCase(String registrationNumber);
}
