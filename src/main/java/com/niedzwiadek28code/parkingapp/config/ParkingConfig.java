package com.niedzwiadek28code.parkingapp.config;

import java.time.format.DateTimeFormatter;

public class ParkingConfig {
    public static final String DATA_PATTERN = "yyyy.MM.dd HH:mm";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final int refreshFrequency = 300000;
}
