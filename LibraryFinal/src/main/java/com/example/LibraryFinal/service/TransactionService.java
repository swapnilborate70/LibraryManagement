package com.example.LibraryFinal.service;

import com.example.LibraryFinal.dto.BookDto;
import com.example.LibraryFinal.model.Transaction;

public interface TransactionService {

    public String submitBook(Transaction transaction);

    public BookDto borrowBook(Transaction transaction);

}
