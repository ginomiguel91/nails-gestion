package com.sis.nailsgestion.services;


import com.sis.nailsgestion.models.SchedulerTaskDto;
import org.springframework.core.io.Resource;

import java.util.List;

public interface SchedulerTaskService {

    List<SchedulerTaskDto> getSchedulerTasks();

    SchedulerTaskDto getSchedulerTaskById(Long id);

    SchedulerTaskDto createSchedulerTask(SchedulerTaskDto schedulerTaskDto);

    SchedulerTaskDto updateSchedulerTask(SchedulerTaskDto schedulerTaskDto, Long id);

    void removeSchedulerTaskById(Long id);

    Double getTotalAmountBySchedulerTask(Long id);

    Resource getPdfSchedulerTask(Long id);
}
