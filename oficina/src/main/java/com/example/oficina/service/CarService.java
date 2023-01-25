package com.example.oficina.service;

import com.example.oficina.domain.Car;
import com.example.oficina.domain.Costumer;
import com.example.oficina.mapper.CarMapper;
import com.example.oficina.repository.CarRepository;
import com.example.oficina.request.CarPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CostumerService costumerService;

    public List<Car> listAll() {
        return carRepository.findAll();
    }

    public Car listById(long id) {
        return carRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car id not found"));
    }

    public Car newCar(CarPostRequestBody carPostRequestBody) {
        Car car = CarMapper.INSTANCE.toCar(carPostRequestBody);
        Costumer costumer = costumerService.listById(carPostRequestBody.getCostumerId());
        car.setCostumer(costumer);

        return carRepository.save(car);
    }

}
