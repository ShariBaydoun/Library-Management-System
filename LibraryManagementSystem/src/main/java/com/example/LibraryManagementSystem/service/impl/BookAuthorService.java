package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookAuthorService {
    List<Book> findAllBooksByAuthor(UUID authorId);
}
