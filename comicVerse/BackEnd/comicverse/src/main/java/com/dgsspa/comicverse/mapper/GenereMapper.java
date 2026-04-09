package com.dgsspa.comicverse.mapper;

import com.dgsspa.comicverse.dto.GenereDTO;
import com.dgsspa.comicverse.model.Genere;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {FumettoMapper.class})
public interface GenereMapper {
    GenereDTO toDTO(Genere genere);
    Genere toEntity(GenereDTO genereDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(GenereDTO dto, @MappingTarget Genere entity);
}