package com.example.LibraryFinal.service.impl;

import com.example.LibraryFinal.dto.BookDto;
import com.example.LibraryFinal.dto.StudentDto;
import com.example.LibraryFinal.exception.CustomeException;
import com.example.LibraryFinal.exception.ResourceNotFoundException;
import com.example.LibraryFinal.model.Book;
import com.example.LibraryFinal.model.Student;
import com.example.LibraryFinal.repository.BookRepository;
import com.example.LibraryFinal.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService
{
    static final String response = "Book not found with ID :";

    @Autowired
    ModelMapper modelMapper;


    @Autowired
    BookRepository bookRepository;
    @Override
    public BookDto createBook(BookDto bookDto)
    {
        Book book = modelMapper.map(bookDto,Book.class);
        bookRepository.save(book);
        return modelMapper.map(book,BookDto.class);
    }

    @Override
    public BookDto getBook(long bookId)
    {
        Book book = bookRepository.findById(bookId).orElseThrow(()->
                new ResourceNotFoundException(response+" "+bookId));
        BookDto bookDto = modelMapper.map(book,BookDto.class);
        return bookDto;
    }

    @Override
    public List<BookDto> getAllBook() {

        List<Book> allBooks = bookRepository.findAll();
        List<BookDto> allBookDto = new ArrayList<>();

        for (Book book : allBooks)
        {
            BookDto bookDto = modelMapper.map(book,BookDto.class);
            allBookDto.add(bookDto);
        }
        return allBookDto;

    }

    @Override
    public void deleteBook(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()->
                new ResourceNotFoundException(response+" "+bookId));
        bookRepository.deleteById(bookId);

    }

    @Override
    public BookDto updateBook(BookDto bookDto)
    {

       Book updatedBook = modelMapper.map(bookDto,Book.class);
       if(bookRepository.existsById(updatedBook.getId()))
       {
           bookRepository.save(updatedBook);
       }
       else
       {
           throw new ResourceNotFoundException(response+" "+updatedBook.getId());
       }
       return modelMapper.map(updatedBook,BookDto.class);
    }

    @Override
    public StudentDto getBorrower(long bookId) {
        StudentDto studentDto = null;
        Book book = bookRepository.findById(bookId).orElseThrow(()->
                new ResourceNotFoundException(response+" "+bookId));

        Student student = book.getStudent();
        if (student == null)
        {
            throw new CustomeException("Book is not borrowed by anyone. book ID: "+" "+bookId);
        }
        else
        {
           studentDto = modelMapper.map(student,StudentDto.class);
        }
        return studentDto;
    }




    //
    //
    //
    @Override
    public List<Book> getSortedBook(String field) {
       return bookRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }


}
