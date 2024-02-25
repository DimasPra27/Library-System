package com.Library.LibrarySystem.dto.Customer;

import lombok.Data;

@Data
public class CreateOrUpdateCustomerRequest {
    private String customerName;
    private String phone;
    private String email;
}
