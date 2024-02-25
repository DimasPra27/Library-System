package com.Library.LibrarySystem.dto.Customer;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {
    private Date createdAt;
    private String customerName;
    private String phone;
    private String email;
}
