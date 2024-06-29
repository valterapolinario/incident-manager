package com.br.manager.mapper;

import com.br.manager.dto.api.request.IncidentReqDTO;
import com.br.manager.dto.api.request.IncidentUpdateReqDTO;
import com.br.manager.dto.api.response.IncidentResDTO;
import com.br.manager.model.IncidentDB;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IncidentMapper {

    IncidentDB toEntity(IncidentReqDTO request);

    IncidentResDTO toResponse(IncidentDB entity);

    @Mapping(target = "id", ignore = true)
    IncidentDB toModelUpdate(IncidentUpdateReqDTO request, @MappingTarget IncidentDB entity);
}
