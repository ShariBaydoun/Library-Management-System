package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.BookAuthor;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.repository.BookAuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookAuthorServiceImpl implements   BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;
    private final AuthorRepository authorRepository;
    @Override
    public List<Book> findAllBooksByAuthor(UUID authorId) {
        Author author=authorRepository.findById(authorId).orElseThrow(()->new RuntimeException("No author found"));
        List<BookAuthor> bookAuthors = bookAuthorRepository.findByAuthorId(authorId);

        // Extract the list of Book entities from BookAuthor
        List<Book> books = bookAuthors.stream()
                .map(BookAuthor::getBook)  // Get Book from each BookAuthor
                .collect(Collectors.toList());

        return books;
    }
}
