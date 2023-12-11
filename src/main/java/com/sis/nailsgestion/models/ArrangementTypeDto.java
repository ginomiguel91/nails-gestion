package com.sis.nailsgestion.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArrangementTypeDto {
    Long id;
    String description;
    Double price;
}
