package com.sis.nailsgestion.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "arrangement_type")
public class ArrangementType implements Serializable {
    @Serial
    private static final long serialVersionUID = 1890849696531068674L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    Double price;
    @ManyToMany(mappedBy = "arrangementTypes", cascade = {CascadeType.ALL})
    private List<SchedulerTask> schedulerTaskList;
}
