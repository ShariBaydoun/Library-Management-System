package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.model.Status;
import com.example.LibraryManagementSystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    int countByBorrowerId(UUID borrowerId);
    List<Transaction> findByBookIdAndBorrowerIdAndStatus (UUID bookId, UUID borrowerId, Status status);

}
