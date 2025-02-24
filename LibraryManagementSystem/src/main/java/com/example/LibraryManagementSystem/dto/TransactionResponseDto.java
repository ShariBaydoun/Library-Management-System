package com.example.LibraryManagementSystem.dto;

import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Borrower;
import com.example.LibraryManagementSystem.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {
    private UUID id;
    private BookResponseDto book;
    private BorrowerResponseDto borrower;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Status status;
}
