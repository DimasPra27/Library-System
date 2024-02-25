package com.Library.LibrarySystem.mapper;

import com.Library.LibrarySystem.domain.entity.Customer;
import com.Library.LibrarySystem.dto.Customer.CreateOrUpdateCustomerResponse;
import com.Library.LibrarySystem.dto.Customer.CustomerDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-25T11:26:40+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Amazon.com Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setCreatedAt( customer.getCreatedAt() );
        customerDTO.setCustomerName( customer.getCustomerName() );
        customerDTO.setPhone( customer.getPhone() );
        customerDTO.setEmail( customer.getEmail() );

        return customerDTO;
    }

    @Override
    public CreateOrUpdateCustomerResponse convertCustomerToCreateOrUpdateCustomerResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CreateOrUpdateCustomerResponse createOrUpdateCustomerResponse = new CreateOrUpdateCustomerResponse();

        createOrUpdateCustomerResponse.setCreatedAt( customer.getCreatedAt() );
        createOrUpdateCustomerResponse.setCustomerName( customer.getCustomerName() );
        createOrUpdateCustomerResponse.setPhone( customer.getPhone() );
        createOrUpdateCustomerResponse.setEmail( customer.getEmail() );

        return createOrUpdateCustomerResponse;
    }
}
