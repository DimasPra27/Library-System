package com.Library.LibrarySystem.repository;

import com.Library.LibrarySystem.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerName(String customerName);

    List<Customer> findByDeletedAtIsNull();

    Optional<Customer> findByDeletedAtIsNullAndId(Long id);

    Customer findUserByEmail(String email);

}
