package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.model.Availability;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Status;
import com.example.LibraryManagementSystem.model.Transaction;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.repository.BorrowerRepository;
import com.example.LibraryManagementSystem.repository.TransactionRepository;
import com.example.LibraryManagementSystem.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final BorrowerRepository borrowerRepository;
    private final BookRepository bookRepository;
    @Override
    public Transaction borrowBook(UUID borrowerId, UUID bookId) {
        borrowerRepository.findById(borrowerId).orElseThrow(()->new RuntimeException("Borrower not found"));
        Book book=bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book not found"));
        Transaction transaction=new Transaction();
        if(!book.getAvailability().equals(Availability.AVAILABLE)) {
            throw new RuntimeException("Book is unavailable");
        }
            book.setAvailability(Availability.UNAVAILABLE);
            transaction.setStatus(Status.BORROWED);
            transaction.setBorrowdate(LocalDate.now());
        return transactionRepository.save(transaction);

    }

    @Override
    public Transaction returnBook(UUID borrowerId, UUID bookId) {
        borrowerRepository.findById(borrowerId).orElseThrow(()->new RuntimeException("Borrower not found"));
        Book book=bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book not found"));
        Transaction transaction=new Transaction();
        if(!book.getAvailability().equals(Availability.UNAVAILABLE)) {
            throw new RuntimeException("Book is already returned ");
        }
        book.setAvailability(Availability.AVAILABLE);
        transaction.setStatus(Status.RETURNED);
        transaction.setReturndate(LocalDate.now());
        return transactionRepository.save(transaction);
    }
}
