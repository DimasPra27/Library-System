package com.Library.LibrarySystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
        System.out.println("Testing");
    }

//    @Override
//    public void run(String... args) throws Exception {
//        bookRepository.findAll().forEach(book -> {
//            System.out.println(book.toString());
//        });
//    }

}
