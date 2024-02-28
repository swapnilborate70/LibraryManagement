package com.example.LibraryFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private long fine;

    @NotBlank
    private String gender;

    @NotNull
    @Min(value = 1000000000, message = "Phone number must be at least 10 digits long")
    private long phone;

    @NotBlank
    private String address;

    @NotNull
    private int grade;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Book> book;
}
