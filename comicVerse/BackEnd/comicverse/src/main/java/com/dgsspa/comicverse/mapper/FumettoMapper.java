package com.dgsspa.comicverse.mapper;

import com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.model.Fumetto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FumettoMapper {
    FumettoDTO toDTO(Fumetto fumetto);

    Fumetto toEntity(FumettoDTO fumettoDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(FumettoDTO dto, @MappingTarget Fumetto entity);
}