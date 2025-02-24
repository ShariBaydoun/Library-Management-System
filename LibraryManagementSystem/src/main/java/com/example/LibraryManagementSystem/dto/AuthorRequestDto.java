package com.example.LibraryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AuthorRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String biography;
}
