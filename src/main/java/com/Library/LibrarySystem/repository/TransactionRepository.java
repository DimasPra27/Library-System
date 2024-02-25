package com.Library.LibrarySystem.repository;

import com.Library.LibrarySystem.domain.entity.Transaction;
import com.Library.LibrarySystem.domain.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findTopByDeletedAtIsNullAndCustomerIdAndStatusOrderByIdDesc(Long customerId, TransactionStatus transactionStatus);

    Optional<Transaction> findTopByDeletedAtIsNullAndCustomerIdOrderByIdDesc(Long customerId);

}
