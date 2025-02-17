package com.example.LibraryManagementSystem.dto;

import com.example.LibraryManagementSystem.model.Availability;
import com.example.LibraryManagementSystem.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BookResponseDto {
    private UUID id;
    private String title;
    private String isbn;
    private Category category;
    private String authorname;
    private Availability availability;
}
