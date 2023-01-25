package com.example.oficina.repository;

import com.example.oficina.domain.Car;
import com.example.oficina.domain.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder,Long> {
    List<ServiceOrder> findByCar(Car car);
}
