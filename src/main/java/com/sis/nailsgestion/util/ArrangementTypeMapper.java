package com.sis.nailsgestion.util;

import com.sis.nailsgestion.models.ArrangementType;
import com.sis.nailsgestion.models.ArrangementTypeDto;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArrangementTypeMapper {
    ArrangementTypeDto convertToDto(ArrangementType arrangementType);

    ArrangementType convertToEntity(ArrangementTypeDto arrangementTypeDto);

    ArrangementTypeDto convertToSameTypeDto(ArrangementTypeDto arrangementTypeDto);

    List<ArrangementTypeDto> mapToListDto(List<ArrangementType> arrangementTypeList);
}
