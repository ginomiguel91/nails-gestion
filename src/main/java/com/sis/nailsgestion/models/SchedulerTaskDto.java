package com.sis.nailsgestion.models;


import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchedulerTaskDto {

    Long id;

    Date appointmentDate;
    LocalTime hourStart;
    LocalTime hourEnd;
    private List<CustomerDto> customers;
    private List<ArrangementTypeDto> arrangementTypes;
}
