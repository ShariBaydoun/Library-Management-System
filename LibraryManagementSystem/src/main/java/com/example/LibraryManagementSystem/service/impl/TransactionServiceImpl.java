package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.client.EmailClient;
import com.example.LibraryManagementSystem.model.Availability;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Status;
import com.example.LibraryManagementSystem.model.Transaction;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.repository.BorrowerRepository;
import com.example.LibraryManagementSystem.repository.TransactionRepository;
import com.example.LibraryManagementSystem.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final BorrowerRepository borrowerRepository;
    private final BookRepository bookRepository;
    private final LimitConfigService limitConfigService;
    private final EmailClient emailClient;
    @Override
    public Transaction borrowBook(UUID borrowerId, UUID bookId,String borrowerEmail) {
        // Validate borrower
        borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new RuntimeException("Borrower not found"));

        // Validate book
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check book availability
        if (!book.getAvailability().equals(Availability.AVAILABLE)) {
            throw new RuntimeException("Book is unavailable");
        }
        String subject = "Book Borrowed";
        String text = "Book"+book.getTitle()+"borrowed successfully";
         emailClient.sendEmail(borrowerEmail,subject,text);
        // Check if borrower has exceeded the transaction limit
        if (!canBorrow(borrowerId)) {
            throw new RuntimeException("Transaction limit exceeded. Cannot borrow more books.");
        }

        // Create new transaction
        Transaction transaction = new Transaction();
        transaction.setStatus(Status.BORROWED);
        transaction.setBorrowDate(LocalDate.now());
        transaction.setBorrower(borrowerRepository.getReferenceById(borrowerId)); // Set borrower
        transaction.setBook(book); // Set book

        // Update book availability
        book.setAvailability(Availability.UNAVAILABLE);
        bookRepository.save(book); // Save book status change

        return transactionRepository.save(transaction); // Save transaction
    }

    private boolean canBorrow(UUID borrowerId) {
        int currentTransactions =transactionRepository.countByBorrowerId(borrowerId);
        int maxLimit = limitConfigService.getTransactionLimit();

        return currentTransactions < maxLimit; // Allow only if below limit
    }

    @Override
    public Transaction returnBook(UUID borrowerId, UUID bookId) {
        borrowerRepository.findById(borrowerId).orElseThrow(()->new RuntimeException("Borrower not found"));
        Book book=bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book not found"));
        List<Transaction> transaction= transactionRepository.findByBookIdAndBorrowerIdAndStatus(bookId,borrowerId,Status.BORROWED);
        if(transaction.isEmpty()){
            throw new RuntimeException("Transaction for book "+bookId +"and borrower "+borrowerId+" not found");
        }
        if(!book.getAvailability().equals(Availability.UNAVAILABLE)) {
            throw new RuntimeException("Book is already returned ");
        }
        book.setAvailability(Availability.AVAILABLE);
        bookRepository.save(book);
        transaction.get(0).setStatus(Status.RETURNED);
        transaction.get(0).setReturnDate(LocalDate.now());
        return transactionRepository.save(transaction.get(0));
    }
}
