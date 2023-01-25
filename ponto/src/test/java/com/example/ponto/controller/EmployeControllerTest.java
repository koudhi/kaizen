package com.example.ponto.controller;

import com.example.ponto.domain.Employe;
import com.example.ponto.service.EmployeService;
import com.example.ponto.util.EmployeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class EmployeControllerTest {
    @InjectMocks
    private EmployeController employeController;

    @Mock
    private EmployeService employeServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Employe> employePage = new PageImpl<>(Arrays.asList(EmployeCreator.createEmploye()));
        List<Employe> employeList = Arrays.asList(EmployeCreator.createEmploye());
        BDDMockito.when(employeServiceMock.findAll())
                .thenReturn(employeList);
    }

    @Test
    void list_ReturnListOfEmployes_WhenSucessful(){
        Employe expectedEmploye = EmployeCreator.createEmploye();
        List<Employe> employeList = employeController.findAll().getBody();
        Assertions.assertThat(employeList).isNotEmpty().hasSize(1);
        Assertions.assertThat(employeList.get(0).getName()).isEqualTo(expectedEmploye.getName());
    }

}