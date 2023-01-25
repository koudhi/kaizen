package com.example.ponto.repository;

import com.example.ponto.domain.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe,Long> {
    List<Employe> findByName(String name);
    Optional<Employe> findByCode(Long code);
}
