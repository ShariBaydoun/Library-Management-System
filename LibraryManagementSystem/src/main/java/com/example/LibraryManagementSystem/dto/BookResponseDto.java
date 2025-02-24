package com.example.LibraryManagementSystem.dto;

import com.example.LibraryManagementSystem.model.Availability;
import com.example.LibraryManagementSystem.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private UUID id;
    private String title;
    private String isbn;
    private Category category;
    private String authorName;
    private Availability availability;
}
