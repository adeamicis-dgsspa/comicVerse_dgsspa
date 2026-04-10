package com.dgsspa.comicverse.mapper;

import com.dgsspa.comicverse.dto.GenereDTO;
import com.dgsspa.comicverse.model.Genere;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GenereMapper {

    @Mapping(target = "idFumetti", expression = "java(genere.getFumetti().stream().map(f -> f.getId()).collect(java.util.stream.Collectors.toList()))")
    GenereDTO toDTO(Genere genere);

    @Mapping(target = "fumetti", ignore = true)
    @Mapping(target = "id", ignore = true)
    Genere toEntity(GenereDTO genereDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fumetti", ignore = true)
    void updateEntityFromDTO(GenereDTO dto, @MappingTarget Genere entity);
}