package com.example.LibraryManagementSystem.dto;

import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Borrower;
import com.example.LibraryManagementSystem.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class TransactionRequestDto {
    private Book book;
    private Borrower borrower;
    private LocalDate borrowdate;
    private LocalDate returndate;
    private Status status;
}
