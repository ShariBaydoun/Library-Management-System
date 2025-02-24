package com.example.LibraryManagementSystem.dto;

import com.example.LibraryManagementSystem.model.Availability;
import com.example.LibraryManagementSystem.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class BookRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String isbn;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Availability availability;

}
