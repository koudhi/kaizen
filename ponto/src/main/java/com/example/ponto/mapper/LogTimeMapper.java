package com.example.ponto.mapper;

import com.example.ponto.domain.LogTime;
import com.example.ponto.request.LogTimePostBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LogTimeMapper {
    LogTimeMapper INSTANCE = Mappers.getMapper(LogTimeMapper.class);

    LogTime toLogTime(LogTimePostBody logTimePostBody);
}
