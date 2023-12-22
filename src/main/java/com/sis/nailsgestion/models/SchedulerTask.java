package com.sis.nailsgestion.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "scheduler_task")

public class SchedulerTask implements Serializable {
    @Serial
    private static final long serialVersionUID = 3553321449683043134L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Temporal(value = TemporalType.DATE)

    Date appointmentDate;
    @Temporal(TemporalType.TIME)
    LocalTime hourStart;
    @Temporal(TemporalType.TIME)
    LocalTime hourEnd;


    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "scheduler_customer",
            joinColumns = @JoinColumn(name = "scheduler_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<Customer> customers;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "scheduler_arrangement",
            joinColumns = @JoinColumn(name = "scheduler_id"),
            inverseJoinColumns = @JoinColumn(name = "arrangement_id")
    )

    private List<ArrangementType> arrangementTypes;


}
