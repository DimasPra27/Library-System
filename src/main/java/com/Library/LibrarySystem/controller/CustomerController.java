package com.Library.LibrarySystem.controller;

import com.Library.LibrarySystem.domain.entity.Customer;
import com.Library.LibrarySystem.dto.Book.LoginRes;
import com.Library.LibrarySystem.dto.Customer.AuthRequest;
import com.Library.LibrarySystem.dto.Customer.CreateOrUpdateCustomerRequest;
import com.Library.LibrarySystem.dto.Customer.CreateOrUpdateCustomerResponse;
import com.Library.LibrarySystem.dto.Customer.CustomerDTO;
import com.Library.LibrarySystem.service.CustomerService;
import com.Library.LibrarySystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

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

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateOrUpdateCustomerRequest request) {
        CreateOrUpdateCustomerResponse createdCustomer = customerService.saveCustomer(request);
        if (createdCustomer == null)
            return new ResponseEntity<>("Customer is not created", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody AuthRequest authRequest) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getCustomerName()));
            String email = authentication.getName();
            Customer user = new Customer()
                    .setEmail(authRequest.getEmail());
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(email, token);

            return ResponseEntity.ok(loginRes);

        } catch (BadCredentialsException e) {
            System.out.println("Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
