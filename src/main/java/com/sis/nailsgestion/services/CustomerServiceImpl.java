package com.sis.nailsgestion.services;

import com.sis.nailsgestion.util.CustomerMapper;
import com.sis.nailsgestion.util.NotFoundException;
import com.sis.nailsgestion.daos.CustomerRepository;
import com.sis.nailsgestion.models.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    static final String MSG_EXCEPTION_CUSTOMER = "¡ Not found customer !";

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDto> getCustomers() {

        List<CustomerDto> customerDtoListEmpty = new ArrayList<>();

        if (customerRepository.findAll().isEmpty()) {
            return customerDtoListEmpty;
        }
        return customerMapper.mapToListDto(customerRepository.findAll().stream().toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        if (!customerRepository.existsById(id)) {
            return null;
        }
        return customerMapper.convertToDto(customerRepository.findById(id).orElseThrow(() -> new NotFoundException((MSG_EXCEPTION_CUSTOMER))));
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }
        return customerMapper.convertToDto(customerRepository.save(customerMapper.convertToEntity(customerDto)));
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long id) {
        if (customerDto == null) {
            return null;
        }

        CustomerDto updatedCustomer = customerMapper.convertToDto(customerRepository.save(customerMapper.convertToEntity(customerDto)));
        updatedCustomer.setId(id);


        return updatedCustomer;
    }

    @Override
    public void removeCustomerById(Long id) {

        if (!customerRepository.existsById(id)) {
            throw new NotFoundException(MSG_EXCEPTION_CUSTOMER);
        }
        customerRepository.deleteById(id);

    }

    @Override
    public List<CustomerDto> findCustomersByAppointmentDate(Date appointmentDate) {
        return customerMapper.mapToListDto(customerRepository.findCustomersByAppointmentDate(appointmentDate));
    }

    @Override
    public List<CustomerDto> findCustomersByArrangement(String description) {
        return customerMapper.mapToListDto(customerRepository.findCustomersByArrangement(description));
    }

    @Override
    public List<CustomerDto> findCustomersByDateRange(Date startDate, Date endDate) {
        return customerMapper.mapToListDto(customerRepository.findCustomersByDateRange(startDate, endDate));
    }
}
