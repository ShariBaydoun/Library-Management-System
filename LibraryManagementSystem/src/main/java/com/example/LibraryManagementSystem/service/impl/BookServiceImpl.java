package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.dto.BookRequestDto;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private ModelMapper modelMapper=new ModelMapper();
    @Override
    public Book addBook(BookRequestDto bookRequestDto) {
        return bookRepository.save(modelMapper.map(bookRequestDto,Book.class));
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
            book.setIsbn(bookRequestDto.getIsbn());
        }
        if (bookRequestDto.getCategory() != null) {
            book.setCategory(bookRequestDto.getCategory());
        }
        if (bookRequestDto.getAuthorname() != null) {
            book.setAuthorname(bookRequestDto.getAuthorname());
        }
        if (bookRequestDto.getAvailability() != null) {
            book.setAvailability(bookRequestDto.getAvailability());
        }

        // Save and return the updated book
        return bookRepository.save(book);
    }


    @Override
    public Book viewBook(UUID bookId) {
        return bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book not found"));
    }

    @Override
    public void deleteBook(UUID bookId) {
        Book book=bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }

        }



