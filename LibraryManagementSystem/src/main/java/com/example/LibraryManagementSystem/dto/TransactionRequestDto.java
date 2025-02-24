package com.example.LibraryManagementSystem.dto;

import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Borrower;
import com.example.LibraryManagementSystem.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class TransactionRequestDto {
    @NotNull
    private Book book;
    @NotNull
    private Borrower borrower;
    @NotNull
    private LocalDate borrowDate;
    @NotNull
    private LocalDate returnDate;
    @NotNull
    private Status status;
}
