package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.BookRequestDto;
import com.example.LibraryManagementSystem.dto.BookResponseDto;
import com.example.LibraryManagementSystem.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/Book")
@RestController
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<BookResponseDto> addBook(@RequestBody BookRequestDto bookRequestDto){
        return new ResponseEntity<>((modelMapper.map(bookService.addBook(bookRequestDto),BookResponseDto.class)),
        HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@RequestBody BookRequestDto bookRequestDto, @PathVariable UUID id){
        return new ResponseEntity<>((modelMapper.map(bookService.updateBook(id,bookRequestDto),BookResponseDto.class))
        ,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> viewBook(@PathVariable UUID id){
        return new ResponseEntity<>((modelMapper.map(bookService.viewBook(id),BookResponseDto.class)),
                HttpStatus.FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id){
        bookService.deleteBook(id);
        return  ResponseEntity.ok("Book Deleted");
    }
}
