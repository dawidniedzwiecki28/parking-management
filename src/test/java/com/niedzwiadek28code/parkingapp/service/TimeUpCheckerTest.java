package com.niedzwiadek28code.parkingapp.service;

import com.niedzwiadek28code.parkingapp.entity.Car;
import com.niedzwiadek28code.parkingapp.utils.TimeUpChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeUpCheckerTest {


    @ParameterizedTest
    @MethodSource("carsProvider")
    void should_passTimeUpChecker_when_timeUpCheckerWorkingCorrectly(boolean expectedResult, Car car) {
        // given
        final LocalDateTime LOCAL_DATE = LocalDateTime.of(2022, 8, 18, 12, 0);
        TimeUpChecker checker = new TimeUpChecker();

        // when
        boolean result = checker.timeUpCheckerForTest(LOCAL_DATE, car.getDepartureDate());

        // then
        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> carsProvider() {
        final Arguments arg1 = Arguments.of(
                true,
                new Car("a", "b",
                        LocalDateTime.of(2022, 8, 18, 9, 0),
                        true,
                        LocalDateTime.of(2022, 8, 18, 11, 45))
        );
        final Arguments arg2 = Arguments.of(
                false,
                new Car("a", "b",
                        LocalDateTime.of(2022, 8, 18, 9, 0),
                        true,
                        LocalDateTime.of(2022, 8, 18, 12, 15))
        );
        final Arguments arg3 = Arguments.of(
                false,
                new Car("a", "b",
                        LocalDateTime.of(2022, 8, 18, 10, 0),
                        true,
                        LocalDateTime.of(2022, 8, 18, 12, 0))
        );
        final Arguments arg4 = Arguments.of(
                false,
                new Car("a",
                        LocalDateTime.of(2022, 8, 18, 9, 0))
        );

        return Stream.of(arg1, arg2, arg3, arg4);
    }
}