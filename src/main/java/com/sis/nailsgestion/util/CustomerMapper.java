package com.sis.nailsgestion.util;

import com.sis.nailsgestion.models.Customer;
import com.sis.nailsgestion.models.CustomerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto convertToDto(Customer customer);

    Customer convertToEntity(CustomerDto customerDto);

    CustomerDto convertToSameTypeDto(CustomerDto customerDto);

    List<CustomerDto> mapToListDto(List<Customer> customerList);
}
