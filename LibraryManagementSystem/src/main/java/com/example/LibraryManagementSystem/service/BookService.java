package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.BookRequestDto;
import com.example.LibraryManagementSystem.model.Book;

import java.util.UUID;

public interface BookService {
    Book addBook(BookRequestDto bookRequestDto);
    Book updateBook(UUID bookId,BookRequestDto bookRequestDto);
    Book viewBook(UUID bookId);
    void deleteBook(UUID bookId);



}
