package com.example.LibraryFinal.service;

import com.example.LibraryFinal.dto.BookDto;
import com.example.LibraryFinal.dto.StudentDto;

import java.util.List;

public interface StudentService {

    public StudentDto createStudent(StudentDto studentDto);

    public StudentDto getStudent(long studentId);

    public List<StudentDto> getAllStudents();

    public void deleteStudent(long studentId);

    public StudentDto updateStudent(StudentDto studentDto);

    public long getFine(long studentId);

    public void payfine (long studentId);

    public List<BookDto> getBooks(long studentId);

}
