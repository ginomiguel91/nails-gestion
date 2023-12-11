package com.sis.nailsgestion.models;

import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
    Long id;
    String name;
    String lastName;
    String address;
    String identification;
    String phoneNumber;

}
