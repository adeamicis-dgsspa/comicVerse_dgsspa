package com.dgsspa.comicverse.mapper;

import  com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.model.Fumetto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FumettoMapper {
    FumettoDTO toDTO(Fumetto fumetto);

    Fumetto toEntity(FumettoDTO fumettoDTO);
}
