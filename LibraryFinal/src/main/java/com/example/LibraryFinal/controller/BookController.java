package com.example.LibraryFinal.controller;

import com.example.LibraryFinal.dto.ApiResponse;
import com.example.LibraryFinal.dto.BookDto;
import com.example.LibraryFinal.dto.StudentDto;
import com.example.LibraryFinal.model.Book;
import com.example.LibraryFinal.model.Student;
import com.example.LibraryFinal.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController
{
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@Valid  @RequestBody BookDto bookDto)
    {
        logger.info("Received create Book request");
        BookDto bookDto1 = bookService.createBook(bookDto);
        return new ResponseEntity(bookDto1, HttpStatus.CREATED);

    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable long bookId)
    {
        logger.info("Received get Book request");
        BookDto bookDto = bookService.getBook(bookId);
        return new ResponseEntity<>(bookDto,HttpStatus.FOUND);
    }

    @GetMapping("/getall")
    public List<BookDto> getAllBooks()
    {
        logger.info("Received get all Book request");
        return bookService.getAllBook();
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable long bookId)
    {
        logger.info("Received delete Book request");
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(new ApiResponse("Book record deleted",HttpStatus.OK,true),HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto)
    {
        logger.info("Received update Book request");
        bookService.updateBook(bookDto);
        return new ResponseEntity<>(bookDto,HttpStatus.OK);
    }

    @GetMapping("/getBorrower/{bookId}")
    public StudentDto getBorrower(@PathVariable long bookId)
    {
        logger.info("Received get borrower of Book request");
        return bookService.getBorrower(bookId);
    }


    //
    //
    //
    //


    @GetMapping("/sorted/{field}")
    public List<Book> getSortedBook(@PathVariable String field)
    {
       return bookService.getSortedBook(field);
    }

}
