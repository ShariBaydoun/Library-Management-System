package com.example.LibraryManagementSystem.dto;

import com.example.LibraryManagementSystem.model.Availability;
import com.example.LibraryManagementSystem.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
public class BookRequestDto {
    private String title;
    private String isbn;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String authorname;
    @Enumerated(EnumType.STRING)
    private Availability availability;

}
