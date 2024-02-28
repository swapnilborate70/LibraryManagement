package com.example.LibraryFinal.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    @NotNull
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

}
