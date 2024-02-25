package com.Library.LibrarySystem.service;

import com.Library.LibrarySystem.domain.entity.Customer;
import com.Library.LibrarySystem.dto.Customer.CreateOrUpdateCustomerRequest;
import com.Library.LibrarySystem.dto.Customer.CreateOrUpdateCustomerResponse;
import com.Library.LibrarySystem.dto.Customer.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomer();

    CustomerDTO getCustomerById(Long id);

    CreateOrUpdateCustomerResponse saveCustomer(CreateOrUpdateCustomerRequest request);

    CreateOrUpdateCustomerResponse updateCustomer(Long id, CreateOrUpdateCustomerRequest request);

    void deleteCustomer(Long id);

    void softDeleteCustomer(Long id);

    Customer findById(Long id);
}
