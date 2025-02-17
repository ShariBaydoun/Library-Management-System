package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, UUID> {
List<BookAuthor> findByAuthorId(UUID id);
}
