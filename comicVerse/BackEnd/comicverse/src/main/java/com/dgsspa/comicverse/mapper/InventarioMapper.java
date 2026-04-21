package com.dgsspa.comicverse.mapper;


import com.dgsspa.comicverse.dto.InventarioDTO;
import com.dgsspa.comicverse.model.Inventario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface InventarioMapper {
    @Mapping(target = "idFumetto", expression = "java(inventario.getFumetto().getId())")
    InventarioDTO toDTO(Inventario inventario);

    @Mapping(target = "fumetto", ignore = true)
    @Mapping(target = "id", ignore = true)
    Inventario toEntity(InventarioDTO inventarioDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fumetto", ignore = true)
    void updateEntityFromDTO(InventarioDTO dto, @MappingTarget Inventario entity);

}
