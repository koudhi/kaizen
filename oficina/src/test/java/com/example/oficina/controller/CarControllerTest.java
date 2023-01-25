package com.example.oficina.controller;

import com.example.oficina.domain.Car;
import com.example.oficina.request.CarPostRequestBody;
import com.example.oficina.service.CarService;
import com.example.oficina.util.CarCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

//@ExtendWith(SpringExtension.class)
@ExtendWith(SpringExtension.class)
class CarControllerTest {
    @InjectMocks
    private CarController carController;

    @Mock
    private CarService carServiceMock;

    @BeforeEach
    void setUp(){
//        PageImpl<Employe> employePage = new PageImpl<>(Arrays.asList(EmployeCreator.createEmploye()));
        Car baseCar = CarCreator.baseCar();
        List<Car> carList = Arrays.asList(baseCar);
        BDDMockito.when(carServiceMock.listAll()).thenReturn(carList);
        BDDMockito.when(carServiceMock.newCar(ArgumentMatchers.any())).thenReturn(baseCar);
        BDDMockito.when(carServiceMock.listById(ArgumentMatchers.any())).thenReturn(baseCar);
    }


    @Test
    void listAll_ReturnListOfCars_isSucessful() {
        Car expectedCar = CarCreator.baseCar();
        List<Car> carList = carController.listAll().getBody();
        Assertions.assertThat(carList).isNotEmpty();
        assertEqualCars(expectedCar, carList.get(0));
    }

    @Test
    void listById() {
        Car expectedCar = CarCreator.baseCar();
        Car car = carController.listById(1).getBody();
        Assertions.assertThat(car).isNotNull();
        assertEqualCars(expectedCar, car);
    }

    @Test
    void newCar() {
        Car expectedCar = CarCreator.baseCar();
        Car car = carController.newCar(new CarPostRequestBody()).getBody();
        Assertions.assertThat(car).isNotNull();
        assertEqualCars(expectedCar, car);
    }

    private static void assertEqualCars(Car expectedCar, Car car) {
        Assertions.assertThat(car.getYear())
                .isEqualTo(expectedCar.getYear());
        Assertions.assertThat(car.getModel())
                .isEqualTo(expectedCar.getModel());
        Assertions.assertThat(car.getBrand())
                .isEqualTo(expectedCar.getBrand());
        Assertions.assertThat(car.getCostumer())
                .isEqualTo(expectedCar.getCostumer());
    }
}