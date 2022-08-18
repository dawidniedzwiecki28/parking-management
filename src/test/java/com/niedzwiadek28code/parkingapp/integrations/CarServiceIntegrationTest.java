package com.niedzwiadek28code.parkingapp.integrations;

import com.niedzwiadek28code.parkingapp.dao.CarRepository;
import com.niedzwiadek28code.parkingapp.entity.Car;
import com.niedzwiadek28code.parkingapp.service.PaidTimeChecker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class CarServiceIntegrationTest {

    @Autowired
    private CarRepository repository;


    @Test
    void should_PassCreateCar_when_databaseIncludeRightNumberOfCars(){
        // given
        initDB();

        // when
        List<Car> cars = repository.findAll();

        // then
        assertEquals(4, cars.size());

    }

    @Test
    void should_PassPaidTimeChecker_when_(){
        // given
        initDB();
        final LocalDateTime LOCAL_DATE = LocalDateTime.of(2022, 8, 18, 12, 0);
        PaidTimeChecker checker = new PaidTimeChecker(repository);

        // when
        List<Car> cars = repository.findAll();
        checker.checkCarsPaidTime(cars,LOCAL_DATE);

        // then
        for (Car car : cars){
            assertFalse(car.isTimeUp());
        }
    }

    private void initDB() {
        Car car1 = new Car("a", "b",
                LocalDateTime.of(2022, 8, 18, 9, 0),
                true,
                LocalDateTime.of(2022, 8, 18, 12, 45));
        Car car2 = new Car("b", "b",
                LocalDateTime.of(2022, 8, 18, 9, 0),
                false,
                LocalDateTime.of(2022, 8, 19, 12, 15));
        Car car3 = new Car("c", "b",
                LocalDateTime.of(2022, 8, 18, 10, 0),
                true,
                LocalDateTime.of(2022, 8, 18, 12, 0));
        Car car4 = new Car("d",
                LocalDateTime.of(2022, 8, 18, 9, 0),
                false);

        repository.saveAll(asList(car1, car2, car3, car4));

    }
}
