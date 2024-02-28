package com.example.LibraryFinal.service.impl;

import com.example.LibraryFinal.dto.BookDto;
import com.example.LibraryFinal.exception.CustomeException;
import com.example.LibraryFinal.exception.ResourceNotFoundException;
import com.example.LibraryFinal.model.Book;
import com.example.LibraryFinal.model.Student;
import com.example.LibraryFinal.model.Transaction;
import com.example.LibraryFinal.repository.BookRepository;
import com.example.LibraryFinal.repository.StudentRepository;
import com.example.LibraryFinal.repository.TransactionRepository;
import com.example.LibraryFinal.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    static final String bookNotAvailable = "Book not available with ID : ";
    static final String studentNotFound = "Student not found with ID : ";
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public BookDto borrowBook(Transaction transaction) {

        Book book = bookRepository.findById(transaction.getBook().getId()).orElseThrow(() ->
                new ResourceNotFoundException(bookNotAvailable + " " + transaction.getBook().getId()));


        Student student = studentRepository.findById(transaction.getStudent().getId()).orElseThrow(() ->
                new ResourceNotFoundException(studentNotFound + " " + transaction.getStudent().getId()));

        if ((book.getStudent()) == null) {
            if ((student.getFine()) == 0) {

                book.setStudent(student);
                book.setStatus("Borrowed");
                bookRepository.save(book);


                transaction.setBook(book);
                transaction.setStudent(student);
                transaction.setBorrowDate(LocalDate.now());
                transaction.setSubmitDate(null);
                transactionRepository.save(transaction);
            } else
                throw new CustomeException("Student having ID : " + student.getId() + " " + " already have fine. Not allowed to borrow book");

        } else
            throw new CustomeException("Book ID : " + book.getId() + ". " + "This book is already borrowed by someone");
        return modelMapper.map(book, BookDto.class);
    }


    @Override
    public String submitBook(Transaction transaction)
    {
        String response = "Book is not submitted";

        Book book = bookRepository.findById(transaction.getBook().getId()).orElseThrow(() ->
                new ResourceNotFoundException(bookNotAvailable + " : " + transaction.getBook().getId()));

        if (book.getStudent() == null)
        {
            throw new CustomeException("Book is not borrowed by any student");
        }
        else
        {
            List<Transaction> allTransactions = transactionRepository.findAll();

            if (allTransactions.isEmpty())
            {
                throw new CustomeException("No transaction record found");
            }
            else
            {
                for (Transaction tempTransaction : allTransactions)
                {
                    if (tempTransaction.getBook().getId() == book.getId())
                    {
                        if (tempTransaction.getSubmitDate() == null)
                        {
                            LocalDate tempSubmitDate = LocalDate.now();
                            long countDays = ChronoUnit.DAYS.between(tempTransaction.getBorrowDate(), tempSubmitDate);
                            long fine = (countDays / 15) * 10;

                            tempTransaction.setSubmitDate(tempSubmitDate);
                            transactionRepository.save(tempTransaction);

                            Student student = tempTransaction.getStudent();

                            student.setFine(fine);
                            studentRepository.save(student);

                            book.setStudent(null);
                            book.setStatus("Available");
                            bookRepository.save(book);

                            response = "Book is submitted";
                        } else throw new CustomeException("Book is already submitted");
                    }
                }

            }

        }
        return response;
    }
}