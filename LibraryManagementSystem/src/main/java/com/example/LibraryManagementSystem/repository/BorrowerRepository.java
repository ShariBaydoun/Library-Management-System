package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, UUID> {
}
