package com.example.LibraryFinal.service;

import com.example.LibraryFinal.dto.BookDto;
import com.example.LibraryFinal.dto.StudentDto;
import com.example.LibraryFinal.model.Book;
import com.example.LibraryFinal.model.Student;

import java.util.List;

public interface BookService {


    public BookDto createBook(BookDto bookDto);

    public BookDto getBook(long bookId);

    public List<BookDto> getAllBook();

    public void deleteBook(long bookId);

    public BookDto updateBook(BookDto bookDto);

    public StudentDto getBorrower(long bookId);


    //
    //
    //
    public List<Book> getSortedBook(String field);

}
