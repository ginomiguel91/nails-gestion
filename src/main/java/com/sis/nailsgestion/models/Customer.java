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
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = -4628157866897801627L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String lastName;
    String address;
    String identification;
    String phoneNumber;
    @ManyToMany(mappedBy = "customers", cascade = {CascadeType.ALL})
    private List<SchedulerTask> schedulerTaskList;
}
