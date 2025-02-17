package com.example.LibraryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BorrowerResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String phonenumber;
}
