package com.sis.nailsgestion.services;

import com.sis.nailsgestion.models.Customer;
import com.sis.nailsgestion.models.CustomerDto;

import java.util.Date;
import java.util.List;

public interface CustomerService {

    List<CustomerDto> getCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto, Long id);
    void removeCustomerById(Long id);

    List<CustomerDto> findCustomersByAppointmentDate(Date appointmentDate);
    List<CustomerDto> findCustomersByArrangement(String description);

}
