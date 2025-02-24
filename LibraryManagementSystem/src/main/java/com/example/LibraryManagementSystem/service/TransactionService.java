package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.model.Status;
import com.example.LibraryManagementSystem.model.Transaction;

import java.util.UUID;

public interface TransactionService {
    Transaction borrowBook(UUID borrowerId,UUID bookId,String borrowerEmail);
    Transaction returnBook(UUID borrowerId, UUID bookId);
}
