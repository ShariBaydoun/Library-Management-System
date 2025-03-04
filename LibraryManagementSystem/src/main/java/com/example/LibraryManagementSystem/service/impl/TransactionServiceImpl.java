package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.client.BankingServiceClient;
import com.example.LibraryManagementSystem.client.EmailClient;
import com.example.LibraryManagementSystem.dto.BorrowBookRequestDto;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final BorrowerRepository borrowerRepository;
    private final BookRepository bookRepository;
    private final LimitConfigService limitConfigService;
    private final EmailClient emailClient;
    private final BankingServiceClient bankingServiceClient;
    @Override
    public Transaction borrowBook(BorrowBookRequestDto borrowBookRequestDto) {
        // Validate borrower
        borrowerRepository.findById(borrowBookRequestDto.getBorrowerId())
                .orElseThrow(() -> new RuntimeException("Borrower not found"));

        // Validate book
        Book book = bookRepository.findById(borrowBookRequestDto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check book availability
        if (!book.getAvailability().equals(Availability.AVAILABLE)) {
            throw new RuntimeException("Book is unavailable");
        }

        // Ensure bookProperties is not null before accessing
        Map<String, String> bookProperties = book.getBookProperties();
        if (bookProperties == null) {
            throw new RuntimeException("Book properties are missing for book: " + book.getTitle());
        }

        // Extract values safely with default values if necessary
        BigDecimal extraChargePerDay = new BigDecimal(bookProperties.getOrDefault("extra_days_rental_price", "0").toString());
        BigDecimal insuranceFee = new BigDecimal(bookProperties.getOrDefault("insurance_fees", "0").toString());

        // 🔹 Set borrow duration (Default: 7 days)
        int borrowDurationDays = 7; // Base period is 1 week
        BigDecimal basePrice = book.getPrice(); // Assume book has a base price field

        // 🔹 Calculate total charge
        BigDecimal totalAmount = basePrice.add(insuranceFee); // Start with base price + insurance
        if (borrowDurationDays > 7) {
            int extraDays = borrowDurationDays - 7;
            totalAmount = totalAmount.add(extraChargePerDay.multiply(BigDecimal.valueOf(extraDays)));
        }

        bankingServiceClient.processBookTransaction(borrowBookRequestDto.getCardNumber(),totalAmount);


        String subject = "Book Borrowed";
        String text = "Book"+book.getTitle()+"borrowed successfully";
         emailClient.sendEmail(borrowBookRequestDto.getBorrowerEmail(),subject,text);
        // Check if borrower has exceeded the transaction limit
        if (!canBorrow(borrowBookRequestDto.getBorrowerId())) {
            throw new RuntimeException("Transaction limit exceeded. Cannot borrow more books.");
        }

        // Create new transaction
        Transaction transaction = new Transaction();
        transaction.setStatus(Status.BORROWED);
        transaction.setBorrowDate(LocalDate.now());
        transaction.setBorrower(borrowerRepository.getReferenceById(borrowBookRequestDto.getBorrowerId())); // Set borrower
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
    public Transaction returnBook(BorrowBookRequestDto borrowBookRequestDto) {
        // Validate borrower and book existence
        borrowerRepository.findById(borrowBookRequestDto.getBorrowerId()).orElseThrow(() -> new RuntimeException("Borrower not found"));
        Book book = bookRepository.findById(borrowBookRequestDto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));

        // Retrieve the transaction for the book and borrower
        List<Transaction> transactions = transactionRepository.findByBookIdAndBorrowerIdAndStatus(borrowBookRequestDto.getBookId(), borrowBookRequestDto.getBorrowerId(), Status.BORROWED);
        if (transactions.isEmpty()) {
            throw new RuntimeException("Transaction for book " + borrowBookRequestDto.getBookId() + " and borrower " + borrowBookRequestDto.getBorrowerId()+ " not found");
        }

        // Check if the book is already returned
        Transaction transaction = transactions.get(0);
        if (transaction.getStatus().equals(Status.RETURNED)) {
            throw new RuntimeException("Book has already been returned");
        }

        // Get the due date (we assume the due date is 7 days from borrow date)
        LocalDate dueDate = transaction.getBorrowDate().plusDays(7); // Assuming a 7-day loan period

        // Calculate whether the book is returned on time
        boolean isReturnedOnTime = LocalDate.now().isBefore(dueDate) || LocalDate.now().isEqual(dueDate);

        // If the book is returned on time, refund the insurance fee
        if (isReturnedOnTime) {
            BigDecimal insuranceFee = new BigDecimal(book.getBookProperties().getOrDefault("insurance_fees", "0"));

            // Process the refund through CMS
            bankingServiceClient.processRefund(borrowBookRequestDto.getCardNumber(), insuranceFee);
        }

        // Update the book availability and transaction status
        book.setAvailability(Availability.AVAILABLE);
        bookRepository.save(book);

        // Update the transaction status to 'RETURNED' and set the return date
        transaction.setStatus(Status.RETURNED);
        transaction.setReturnDate(LocalDate.now());

        // Save the transaction
        return transactionRepository.save(transaction);
    }

}
