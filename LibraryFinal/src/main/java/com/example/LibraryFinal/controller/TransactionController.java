package com.example.LibraryFinal.controller;

import com.example.LibraryFinal.dto.ApiResponse;
import com.example.LibraryFinal.dto.BookDto;
import com.example.LibraryFinal.model.Transaction;
import com.example.LibraryFinal.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    TransactionService transactionService;

    @PostMapping("/borrow")
    public ResponseEntity<BookDto> borrowBook(@RequestBody Transaction transaction)
    {
        logger.info("Borrow book request received");
        BookDto bookDto =transactionService.borrowBook(transaction);
        return  ResponseEntity.ok(bookDto);

    }

    @PutMapping("/submit")
    public ResponseEntity<String> submitBook(@RequestBody Transaction transaction)
    {

        logger.info("Submit book request received");
        String response = transactionService.submitBook(transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
