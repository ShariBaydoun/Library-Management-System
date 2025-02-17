package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.BookResponseDto;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.service.impl.BookAuthorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bookauthor")
public class BookAuthorController {
    private final BookAuthorService bookAuthorService;
    private ModelMapper modelMapper;
    @GetMapping("/{id}")
    public ResponseEntity<List<BookResponseDto>> findAllBooksByAuthor(@PathVariable UUID authorId){
        List<Book> books = bookAuthorService.findAllBooksByAuthor(authorId);

        // Handling the case when no books are found
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Mapping List<Book> to List<BookResponseDto>
        List<BookResponseDto> bookResponseDtos = books.stream()
                .map(book -> modelMapper.map(book, BookResponseDto.class))
                .toList();

        return new ResponseEntity<>(bookResponseDtos, HttpStatus.OK);

    }
}
