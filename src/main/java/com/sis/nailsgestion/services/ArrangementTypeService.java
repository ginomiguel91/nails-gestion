package com.sis.nailsgestion.services;

import com.sis.nailsgestion.models.ArrangementTypeDto;


import java.util.List;

public interface ArrangementTypeService {
    List<ArrangementTypeDto> getArrangements();

    ArrangementTypeDto getArrangementTypeById(Long id);

    ArrangementTypeDto createArrangementType(ArrangementTypeDto arrangementTypeDto);

    ArrangementTypeDto updateArrangementType(ArrangementTypeDto arrangementTypeDto, Long id);
    void removeArrangementTypeById(Long id);
}
