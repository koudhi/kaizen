package com.example.ponto.repository;

import com.example.ponto.domain.Employe;
import com.example.ponto.util.EmployeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;


@DataJpaTest
class EmployeRepositoryTest {

    @Autowired
    private EmployeRepository employeRepository;


    @Test
    void save_persistEmploye_whenSucessful(){
        Employe employe = EmployeCreator.createEmploye();
        Employe savedEmploye = employeRepository.save(employe);
        Assertions.assertThat(savedEmploye.getName()).isEqualTo("Teste");
        Assertions.assertThat(savedEmploye.getAlerts()).isZero();
        Assertions.assertThat(savedEmploye.getFunction()).isEqualTo("teste");
        Assertions.assertThat(savedEmploye.getCode()).isEqualTo(1234L);

    }

    @Test
    void save_persistEmploye_throwWhenNameIsEmpty(){
        Employe employe = new Employe();
        Assertions.assertThatThrownBy(()->this.employeRepository.save(employe))
                .isInstanceOf(ConstraintViolationException.class);
    }






}