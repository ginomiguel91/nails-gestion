package com.sis.nailsgestion.services;

import com.sis.nailsgestion.util.ArrangementTypeMapper;
import com.sis.nailsgestion.util.NotFoundException;
import com.sis.nailsgestion.daos.ArrangementTypeRepository;
import com.sis.nailsgestion.models.ArrangementTypeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArrangementTypeServiceImpl implements ArrangementTypeService {

    ArrangementTypeRepository arrangementTypeRepository;
    ArrangementTypeMapper arrangementTypeMapper;

    static final String MSG_EXCEPTION_ARRANGEMENT = "¡ Not found arrangement !";

    public ArrangementTypeServiceImpl(ArrangementTypeRepository arrangementTypeRepository, ArrangementTypeMapper arrangementTypeMapper) {
        this.arrangementTypeRepository = arrangementTypeRepository;
        this.arrangementTypeMapper = arrangementTypeMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArrangementTypeDto> getArrangements() {
        List<ArrangementTypeDto> arrangementTypeDtoListEmpty = new ArrayList<>();
        if (arrangementTypeRepository.findAll().isEmpty()) {
            return arrangementTypeDtoListEmpty;
        }
        return arrangementTypeMapper.mapToListDto(arrangementTypeRepository.findAll().stream().toList());

    }

    @Override
    @Transactional(readOnly = true)
    public ArrangementTypeDto getArrangementTypeById(Long id) {
        if (!arrangementTypeRepository.existsById(id)) {
            return null;
        }
        return arrangementTypeMapper.convertToDto(arrangementTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(MSG_EXCEPTION_ARRANGEMENT)));
    }

    @Override
    @Transactional
    public ArrangementTypeDto createArrangementType(ArrangementTypeDto arrangementTypeDto) {
        if (arrangementTypeDto == null) {
            return null;
        }
        return arrangementTypeMapper.convertToDto(arrangementTypeRepository.save(arrangementTypeMapper.convertToEntity(arrangementTypeDto)));
    }

    @Override
    @Transactional
    public ArrangementTypeDto updateArrangementType(ArrangementTypeDto arrangementTypeDto, Long id) {

        if (arrangementTypeDto == null) {
            return null;
        }
        ArrangementTypeDto updatedArrangementTypeDto =
                arrangementTypeMapper.convertToDto(arrangementTypeRepository.save(arrangementTypeMapper.convertToEntity(arrangementTypeDto)));
        updatedArrangementTypeDto.setId(id);

        return updatedArrangementTypeDto;
    }

    @Override
    @Transactional
    public void removeArrangementTypeById(Long id) {

        if (!arrangementTypeRepository.existsById(id)) {
            throw new NotFoundException(MSG_EXCEPTION_ARRANGEMENT);
        }

        arrangementTypeRepository.deleteById(id);

    }
}
