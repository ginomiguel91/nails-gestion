package com.sis.nailsgestion.util;


import com.sis.nailsgestion.models.SchedulerTask;
import com.sis.nailsgestion.models.SchedulerTaskDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchedulerTaskMapper {
    SchedulerTaskDto convertToDto(SchedulerTask schedulerTask);

    SchedulerTask convertToEntity(SchedulerTaskDto schedulerTaskDto);

    SchedulerTaskDto convertToSameTypeDto(SchedulerTaskDto schedulerTaskDto);

    List<SchedulerTaskDto> mapToListDto(List<SchedulerTask> schedulerTaskList);
}
