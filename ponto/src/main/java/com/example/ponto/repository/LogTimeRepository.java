package com.example.ponto.repository;

import com.example.ponto.domain.Employe;
import com.example.ponto.domain.LogTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogTimeRepository extends JpaRepository<LogTime,Long> {
    List<LogTime> findByEmploye(Employe employe);
}
