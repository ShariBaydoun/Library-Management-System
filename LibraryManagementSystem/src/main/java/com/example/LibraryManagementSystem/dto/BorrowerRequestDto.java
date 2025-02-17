package com.example.LibraryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BorrowerRequestDto {
    private String name;
    private String email;
    private String phonenumber;
}
