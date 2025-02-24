package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.dto.BookRequestDto;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.service.BookService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private ModelMapper modelMapper = new ModelMapper();
    private final RestTemplate restTemplate;
    private final AuthorRepository authorRepository;
    private final ApiServiceImpl apiService;
    private  final ObjectMapper objectMapper;

    @Override
    public Book addBook(BookRequestDto bookRequestDto) {
        String isbn = bookRequestDto.getIsbn();
        // 🔹 Fetch book data from Open Library API
        String jsonResponse = apiService.fetchBookData(isbn);
        // 🔹 Extract the author name from JSON response
        String authorName = extractAuthorName(jsonResponse, isbn);

        Book book = modelMapper.map(bookRequestDto, Book.class);
        book.setAuthorName(authorName);
        return bookRepository.save(book);

    }

    private String extractAuthorName(String jsonResponse, String isbn) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode bookNode = root.path("ISBN:" + isbn).path("authors");
            if (bookNode.isArray() && bookNode.size() > 0) {
                return bookNode.get(0).path("name").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown Author"; // Handle cases where author info is missing
    }

    @Override
    public Book updateBook(UUID bookId, BookRequestDto bookRequestDto) {
        // Find the book by ID
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Update fields if they are not null
        if (bookRequestDto.getTitle() != null) {
            book.setTitle(bookRequestDto.getTitle());
        }
        if (bookRequestDto.getIsbn() != null) {
           String isbn= bookRequestDto.getIsbn();
            book.setIsbn(isbn);
            String jsonResponse = apiService.fetchBookData(isbn);
            String authorName = extractAuthorName(jsonResponse, isbn);
            book.setAuthorName(authorName);

        }
        if (bookRequestDto.getCategory() != null) {
            book.setCategory(bookRequestDto.getCategory());
        }

        if (bookRequestDto.getAvailability() != null) {
            book.setAvailability(bookRequestDto.getAvailability());
        }

        // Save and return the updated book
        return bookRepository.save(book);
    }


    @Override
    public Book viewBook(UUID bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public void deleteBook(UUID bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }

}



