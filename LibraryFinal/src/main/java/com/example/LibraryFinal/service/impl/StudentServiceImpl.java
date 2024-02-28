package com.example.LibraryFinal.service.impl;

import com.example.LibraryFinal.dto.BookDto;
import com.example.LibraryFinal.dto.StudentDto;
import com.example.LibraryFinal.exception.CustomeException;
import com.example.LibraryFinal.exception.ResourceNotFoundException;
import com.example.LibraryFinal.model.Student;
import com.example.LibraryFinal.repository.BookRepository;
import com.example.LibraryFinal.repository.StudentRepository;
import com.example.LibraryFinal.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    static final String response = "Student not found with ID : ";


    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto,Student.class);
        Student savedStudent = studentRepo.save(student);
        return modelMapper.map(savedStudent,StudentDto.class);

    }

    @Override
    public StudentDto getStudent(long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(()->
                new ResourceNotFoundException(response+" "+studentId));
        StudentDto studentDto = modelMapper.map(student,StudentDto.class);
        return studentDto;

    }

    @Override
    public List<StudentDto> getAllStudents() {
       List<Student> listAllStudent= studentRepo.findAll();
       List<StudentDto> listStudentDto= new ArrayList<>();

       if(listAllStudent.isEmpty())
       {
            throw new ResourceNotFoundException("Student record not present");
       }
       else
       {
            for (Student student : listAllStudent)
            {
               StudentDto studentDto = modelMapper.map(student,StudentDto.class);
               listStudentDto.add(studentDto);
            }
       }
        return listStudentDto;
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student student = studentRepo.findById(studentDto.getId()).orElseThrow(()->
                new ResourceNotFoundException(response+" "+studentDto.getId()));

        Student updatedStudent = modelMapper.map(studentDto,Student.class);
        studentRepo.save(updatedStudent);
        return studentDto;

    }

    @Override
    public long getFine(long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(()->
                new ResourceNotFoundException(response+" "+studentId));

        return student.getFine();

    }

    @Override
    public void payfine(long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(()->
                new ResourceNotFoundException(response+" "+studentId));
        if (student.getFine()==0)
        {
            throw new CustomeException("Student having student ID : "+studentId+" "+"have fine :"+student.getFine()+". No need to pay fine");
        }
        else student.setFine(0);

        studentRepo.save(student);

    }


    @Override
    public void deleteStudent(long studentId)
    {
        studentRepo.findById(studentId).orElseThrow(()->
                new ResourceNotFoundException(response+" "+studentId));
        studentRepo.deleteById(studentId);
    }


    @Override
    public List<BookDto> getBooks(long studentId) {

        Student student = studentRepo.findById(studentId).orElseThrow(()->
                new ResourceNotFoundException(response+" "+studentId));

        List<BookDto> listBookDto = new ArrayList<>();

        for(int i=0;i<student.getBook().size();i++)
        {
            BookDto bookDto = modelMapper.map(student.getBook().get(i),BookDto.class);
            listBookDto.add(bookDto);
        }
        if (listBookDto.isEmpty())
        {
            throw new CustomeException("Student having student ID : "+studentId+". Does not borrowed any books.");
        }
        return listBookDto;
    }
}
