package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.BorrowBookRequestDto;
import com.example.LibraryManagementSystem.model.Status;
import com.example.LibraryManagementSystem.model.Transaction;

import java.util.UUID;

public interface TransactionService {
    Transaction borrowBook(BorrowBookRequestDto borrowBookRequestDto);
    Transaction returnBook(BorrowBookRequestDto borrowBookRequestDto);
}
