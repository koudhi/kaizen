package com.example.oficina.repository;

import com.example.oficina.domain.CarPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPartsRepository extends JpaRepository<CarPart,Long> {
}
