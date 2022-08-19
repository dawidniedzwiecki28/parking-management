package com.niedzwiadek28code.parkingapp.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeUpChecker {

    public boolean timeUpChecker(LocalDateTime date) {
        if (date == null) {
            return false;
        }

        Duration duration = Duration.between(LocalDateTime.now(), date);

        return (duration.isNegative());
    }
    public boolean timeUpCheckerForTest(LocalDateTime now, LocalDateTime date) {
        if (date == null) {
            return false;
        }

        Duration duration = Duration.between(now, date);

        return (duration.isNegative());
    }
}
