package com.Library.LibrarySystem.domain.entity;

import com.Library.LibrarySystem.domain.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    private Date deletedAt;
    private Date loanDate;
    private Date returnedDate;
    private Long customerId;
    private String customerName;
    private long totalQuantity;
    private long totalFee;
    private long fineFee;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

}
