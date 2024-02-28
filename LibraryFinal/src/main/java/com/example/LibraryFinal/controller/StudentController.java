package com.example.LibraryFinal.controller;

import com.example.LibraryFinal.dto.ApiResponse;
import com.example.LibraryFinal.dto.BookDto;
import com.example.LibraryFinal.dto.StudentDto;
import com.example.LibraryFinal.model.Book;
import com.example.LibraryFinal.model.Student;
import com.example.LibraryFinal.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController
{
    Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto)
    {
        logger.info("Received add Student request");
        StudentDto studentDto1 = studentService.createStudent(studentDto);
        return new ResponseEntity<>(studentDto1, HttpStatus.CREATED);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable long studentId)
    {
        logger.info("Received get Student request");
        StudentDto studentDto = studentService.getStudent(studentId);
        return new ResponseEntity<>(studentDto,HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public List<StudentDto> getAllStudents()
    {
        logger.info("Received get all Student request");
        return studentService.getAllStudents();
    }

    @PutMapping("/update")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto)
    {
        logger.info("Received update Student request");
        studentService.updateStudent(studentDto);
        return new ResponseEntity<>(studentDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable long studentId) {
        logger.info("Received delete Student request");
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(new ApiResponse("Student record deleted",HttpStatus.OK,true),HttpStatus.OK);
    }


    @GetMapping("/fine/{studentId}")
    public ResponseEntity<String> getFine(@PathVariable long studentId)
    {
        String response = "Student having student ID : "+ studentId+" "+" have fine : "+studentService.getFine(studentId);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/payfine/{studentId}")
    public ResponseEntity<String> payfine(@PathVariable long studentId)
    {
        logger.info("Received pay fine request");
        studentService.payfine(studentId);
        return ResponseEntity.ok("Fine paid. No dues.");
    }

    @GetMapping("/getBooks/{studentId}")
    public ResponseEntity<List<BookDto>> getBooks(@PathVariable long studentId)
    {
        logger.info("Received get books borrowed by student request");
        List<BookDto> listBookDto =  studentService.getBooks(studentId);
        return new ResponseEntity<>(listBookDto,HttpStatus.OK);
    }

}
