package com.example.LibraryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowBookRequestDto {
        private String cardNumber;
        private UUID borrowerId;
        private UUID bookId;
        private String borrowerEmail;

    }
