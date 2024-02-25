package com.Library.LibrarySystem.service.impl;

import com.Library.LibrarySystem.domain.entity.Book;
import com.Library.LibrarySystem.domain.entity.DetailTransaction;
import com.Library.LibrarySystem.domain.entity.Transaction;
import com.Library.LibrarySystem.domain.enums.TransactionStatus;
import com.Library.LibrarySystem.dto.Library.OrderBookDTO;
import com.Library.LibrarySystem.dto.Library.ReturnBookDTO;
import com.Library.LibrarySystem.exception.ResourceNotFoundException;
import com.Library.LibrarySystem.repository.DetailTransactionRepository;
import com.Library.LibrarySystem.repository.TransactionRepository;
import com.Library.LibrarySystem.service.BookService;
import com.Library.LibrarySystem.service.CustomerService;
import com.Library.LibrarySystem.service.LibraryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class LibraryServiceImpl implements LibraryService {
    private final BookService bookService;
    private final CustomerService customerService;
    private final TransactionRepository transactionRepository;
    private final DetailTransactionRepository detailTransactionRepository;

    public static final long fin_fee_per_day = 5000;

    @Override
    public boolean insertTransaction(OrderBookDTO requset) {
        log.info("Order book request ==> {}", requset.toString());
        //Check user is valid to borrow the book
        //if user has already borrowed a book, user can't borrow it again
        System.out.println(isCustomerAvailable(requset.getCustomerId()));

        if (!isCustomerAvailable(requset.getCustomerId())) {
            return false;
        }

        List<OrderBookDTO.DetailOrderDTO> detailOrderDTOS = requset.getDetailOrderDTOS();
        List<DetailTransaction> detailTransactions = new ArrayList<>();

        int totalFee = 0;
        int totalQuantity = 0;
        //check the books is valid fot borrow it
        for (OrderBookDTO.DetailOrderDTO d : detailOrderDTOS) {
            if (isBookAvailable(d.getBookId(), d.getDetailQuantity())) {
                final DetailTransaction detailTransaction = new DetailTransaction();
                detailTransactions.add(detailTransaction
                        .setBookId(d.getBookId())
                        .setDetailQuantity(d.getDetailQuantity())
                );

                totalFee += (d.getPrice() * d.getDetailQuantity());
                totalQuantity += d.getDetailQuantity();
            }
        }

        log.info("Order book is valid!!");

        //Check whether the amount borrowed is appropriate,
        //make sure the user can borrow a book otherwise return false
        if (detailTransactions.size() != detailOrderDTOS.size()) {
            return false;
        }

        final Transaction transaction = new Transaction();

        transaction
                .setStatus(TransactionStatus.BORROWED)
                .setFineFee(0)
                .setLoanDate(new Date())
                .setReturnedDate(requset.getReturnedDate())
                .setCustomerId(requset.getCustomerId())
                .setCustomerName(requset.getCustomerName())
                .setTotalQuantity(totalQuantity)
                .setTotalFee(totalFee);
        transactionRepository.save(transaction);


        detailTransactions.forEach(detailTransaction -> {
            Book book = bookService.findById(detailTransaction.getBookId());
            final int quantity = (int) (book.getQuantity() - detailTransaction.getDetailQuantity());
            bookService.updateQuantity(book.getId(), quantity);
            detailTransactionRepository.save(detailTransaction.setTransactionId(transaction.getId()));
        });
        log.info("Order book is Success!!");
        log.info("Order book transaction response ==> {}", transaction);

        return true;
    }

    @Override
    public boolean returnTransaction(ReturnBookDTO returnBookDTO) {
        Transaction transaction = transactionRepository.findTopByDeletedAtIsNullAndCustomerIdAndStatusOrderByIdDesc(returnBookDTO.getCustomerId(), TransactionStatus.BORROWED)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with customer id " + returnBookDTO.getCustomerId()));

        log.info("return book request ==> {}", returnBookDTO);

        List<DetailTransaction> detailTransactions = detailTransactionRepository.findAllByDeletedAtIsNullAndTransactionId(transaction.getId());

        //calculate the fine if you are late
        final long finFee = getDateDiff(transaction.getReturnedDate(), transaction.getLoanDate(), TimeUnit.DAYS) * fin_fee_per_day;
        transactionRepository
                .save(transaction.setStatus(TransactionStatus.RETURNED))
                .setUpdatedAt(new Date())
                .setFineFee(finFee);

        detailTransactions.forEach(detailTransaction -> {
            Book book = bookService.findById(detailTransaction.getBookId());
            final int quantity = (int) (book.getQuantity() + detailTransaction.getDetailQuantity());
            bookService.updateQuantity(book.getId(), quantity);
            detailTransactionRepository.save(detailTransaction.setDeletedAt(new Date()));
        });
        log.info("return book response ==> {}", transaction);

        return true;
    }

    private boolean isBookAvailable(Long id, Long quantityBookBorrowed) {
        Book book = bookService.findById(id);

        if (quantityBookBorrowed > book.getQuantity()) return false;

        return book.getQuantity() > 0;
    }

    private boolean isCustomerAvailable(Long id) {
        customerService.findById(id);

        Optional<Transaction> transaction = transactionRepository.findTopByDeletedAtIsNullAndCustomerIdOrderByIdDesc(id);
        return transaction.isEmpty() || !transaction.get().getStatus().equals(TransactionStatus.BORROWED);
    }

    private long getDateDiff(Date loadDate, Date returnedDate, TimeUnit timeUnit) {
        long diff = returnedDate.getTime() - loadDate.getTime();
        return timeUnit.convert(diff, TimeUnit.MILLISECONDS);
    }
}
