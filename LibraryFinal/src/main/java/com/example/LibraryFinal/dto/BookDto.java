package com.example.LibraryFinal.dto;

import com.example.LibraryFinal.model.Student;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull
    @Min(1)
    private long pages;

    @NotBlank
    private String publisher;

    @NotNull
    private LocalDate publishDate;

    @NotBlank
    private String status;

    @NotBlank
    private String language;


}
