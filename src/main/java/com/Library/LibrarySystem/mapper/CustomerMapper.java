package com.Library.LibrarySystem.mapper;

import com.Library.LibrarySystem.domain.entity.Customer;
import com.Library.LibrarySystem.dto.Customer.CreateOrUpdateCustomerResponse;
import com.Library.LibrarySystem.dto.Customer.CustomerDTO;
import org.mapstruct.Mapper;

import java.util.Formatter;

@Mapper(componentModel = "spring", uses = {Formatter.class})
public interface CustomerMapper {
    CustomerDTO convertCustomerToCustomerDTO(Customer customer);

    CreateOrUpdateCustomerResponse convertCustomerToCreateOrUpdateCustomerResponse(Customer customer);
}
