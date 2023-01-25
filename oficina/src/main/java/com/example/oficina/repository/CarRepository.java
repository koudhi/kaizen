package com.example.oficina.repository;

import com.example.oficina.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository  extends JpaRepository<Car,Long> {

}
