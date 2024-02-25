package com.Library.LibrarySystem.repository;

import com.Library.LibrarySystem.domain.entity.DetailTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTransactionRepository extends JpaRepository<DetailTransaction, Long> {
    List<DetailTransaction> findAllByDeletedAtIsNullAndTransactionId(Long transactionId);
}
