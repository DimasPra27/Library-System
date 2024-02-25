package com.Library.LibrarySystem.service;

import com.Library.LibrarySystem.dto.Library.OrderBookDTO;
import com.Library.LibrarySystem.dto.Library.ReturnBookDTO;

public interface LibraryService {
    boolean insertTransaction(OrderBookDTO orderBookDTO);

    boolean returnTransaction(ReturnBookDTO returnBookDTO);
}
