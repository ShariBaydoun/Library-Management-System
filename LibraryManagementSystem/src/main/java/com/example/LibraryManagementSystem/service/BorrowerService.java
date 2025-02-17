package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.BorrowerRequestDto;
import com.example.LibraryManagementSystem.model.Borrower;

import java.util.UUID;

public interface BorrowerService {
    Borrower addBorrower(BorrowerRequestDto borrowerRequestDto);
    Borrower updateBorrower(UUID id,BorrowerRequestDto borrowerRequestDto);
    Borrower viewBorrower(UUID borrowerId);
    void deleteBorrower(UUID borrowerId);

}
