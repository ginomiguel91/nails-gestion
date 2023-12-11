package com.sis.nailsgestion.controllers;

import com.sis.nailsgestion.models.CustomerDto;
import com.sis.nailsgestion.services.CustomerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        if (customerService.getCustomers().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);

    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {

        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);

    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {

        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Long id) {

        return new ResponseEntity<>(customerService.updateCustomer(customerDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> removeCustomerById(@PathVariable Long id) {
        customerService.removeCustomerById(id);

        return new ResponseEntity<>(HttpStatus.OK);


    }

    @GetMapping("/customers/searchByDate")

    public ResponseEntity<List<CustomerDto>> findCustomersByAppointmentDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date appointmentDate) {
        return new ResponseEntity<>(customerService.findCustomersByAppointmentDate(appointmentDate), HttpStatus.OK);
    }

    @GetMapping("/customers/searchByArrangement")

    public ResponseEntity<List<CustomerDto>> findCustomersByArrangement(@RequestParam String description) {
        return new ResponseEntity<>(customerService.findCustomersByArrangement(description), HttpStatus.OK);
    }

}
