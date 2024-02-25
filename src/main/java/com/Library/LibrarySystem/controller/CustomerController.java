package com.Library.LibrarySystem.controller;

import com.Library.LibrarySystem.domain.entity.Customer;
import com.Library.LibrarySystem.dto.Customer.CreateOrUpdateCustomerRequest;
import com.Library.LibrarySystem.dto.Customer.CreateOrUpdateCustomerResponse;
import com.Library.LibrarySystem.dto.Customer.CustomerDTO;
import com.Library.LibrarySystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerService.findAllCustomer();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public CreateOrUpdateCustomerResponse saveCustomer(@RequestBody CreateOrUpdateCustomerRequest request) {
        return customerService.saveCustomer(request);
    }

    @PutMapping("/{id}")
    public CreateOrUpdateCustomerResponse updateCustomer(@PathVariable("id") Long id,
                                                         @RequestBody CreateOrUpdateCustomerRequest request) {
        return customerService.updateCustomer(id, request);
    }

    //Hard delete
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }

    //Soft Delete
    @PostMapping("/{id}")
    public void softDeleteCustomer(@PathVariable("id") Long id) {
        customerService.softDeleteCustomer(id);
    }

    @PostMapping("/login")
    public String login() {
        return "TOKEN";
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateOrUpdateCustomerRequest request) {
        CreateOrUpdateCustomerResponse createdCustomer = customerService.saveCustomer(request);
        if (createdCustomer == null)
            return new ResponseEntity<>("Customer is not created", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

}
