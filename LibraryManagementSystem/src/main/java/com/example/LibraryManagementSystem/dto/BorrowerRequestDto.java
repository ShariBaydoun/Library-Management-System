package com.example.LibraryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowerRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;
}
