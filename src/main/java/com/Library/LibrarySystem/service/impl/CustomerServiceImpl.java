package com.Library.LibrarySystem.service.impl;

import com.Library.LibrarySystem.domain.entity.Customer;
import com.Library.LibrarySystem.dto.Customer.CreateOrUpdateCustomerRequest;
import com.Library.LibrarySystem.dto.Customer.CreateOrUpdateCustomerResponse;
import com.Library.LibrarySystem.dto.Customer.CustomerDTO;
import com.Library.LibrarySystem.exception.ResourceNotFoundException;
import com.Library.LibrarySystem.exception.ValidationException;
import com.Library.LibrarySystem.mapper.CustomerMapper;
import com.Library.LibrarySystem.repository.CustomerRepository;
import com.Library.LibrarySystem.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findByDeletedAtIsNull();
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        final Customer customer = customerRepository.findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        return customerMapper.convertCustomerToCustomerDTO(customer);
    }

    @Override
    public CreateOrUpdateCustomerResponse saveCustomer(CreateOrUpdateCustomerRequest request) {
        log.info("Create customer request ==> {}", request.toString());
        validateCustomer(request);

        final Customer customer = new Customer()
                .setCustomerName(request.getCustomerName())
                .setEmail(request.getEmail())
                .setPhone(request.getPhone());

        customerRepository.save(customer);
        log.info("Customer create response ==> {}", customer);

        return customerMapper.convertCustomerToCreateOrUpdateCustomerResponse(customer);
    }

    @Override
    public CreateOrUpdateCustomerResponse updateCustomer(Long id, CreateOrUpdateCustomerRequest request) {
        log.info("Update customer request ==> {}", request.toString());
        validateCustomer(request);

        final Customer customer = customerRepository.findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        customer
                .setCustomerName(request.getCustomerName())
                .setEmail(request.getEmail())
                .setPhone(request.getPhone());

        log.info("Update customer response ==> {}", customer);

        customerRepository.save(customer);

        return customerMapper.convertCustomerToCreateOrUpdateCustomerResponse(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void softDeleteCustomer(Long id) {
        final Customer customer = customerRepository.findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        customerRepository.save(customer.setDeletedAt(new Date()));
    }

    @Override
    public Customer findById(Long id) {
        final Customer customer = customerRepository.findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        return customer;
    }

    private void validateCustomer(CreateOrUpdateCustomerRequest request) {
        if (request.getCustomerName() == null || request.getCustomerName().isEmpty()) {
            throw new ValidationException("Invalid Customer Name");
        }

        if (request.getPhone() == null || request.getPhone().isEmpty()) {
            throw new ValidationException("Invalid Customer Phone Number");
        }
    }
}
