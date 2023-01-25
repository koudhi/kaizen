package com.example.ponto.mapper;

import com.example.ponto.domain.Employe;
import com.example.ponto.request.EmployePostBody;
import com.example.ponto.request.EmployePutBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeMapper {
    EmployeMapper INSTANCE = Mappers.getMapper(EmployeMapper.class);
    @Mapping(target = "name", source = "employeName")
    Employe toEmploye(EmployePostBody employePostBody);
    Employe toEmploye(EmployePutBody employePutBody);
}
