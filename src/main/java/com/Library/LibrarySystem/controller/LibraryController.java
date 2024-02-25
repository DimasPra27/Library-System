package com.Library.LibrarySystem.controller;

import com.Library.LibrarySystem.dto.Library.OrderBookDTO;
import com.Library.LibrarySystem.dto.Library.ReturnBookDTO;
import com.Library.LibrarySystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/libraries")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping("/order")
    public boolean InsertTransaction(@RequestBody OrderBookDTO orderBookDTO) {
        return libraryService.insertTransaction(orderBookDTO);
    }

    @PostMapping("/return")
    public boolean ReturnTransaction(@RequestBody ReturnBookDTO returnBookDTO) {
        return libraryService.returnTransaction(returnBookDTO);
    }
}
