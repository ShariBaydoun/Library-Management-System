package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.AuthorRequestDto;
import com.example.LibraryManagementSystem.dto.AuthorResponseDto;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.service.AuthorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequestMapping("/api/author")
@AllArgsConstructor
@RestController
public class AuthorController {
    private final AuthorService authorService;
    private ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<AuthorResponseDto> addAuthor(@Valid @RequestBody AuthorRequestDto authorRequestDto){
        return new ResponseEntity<>((modelMapper.map(authorService.addAuthor(authorRequestDto), AuthorResponseDto.class)),
                HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@RequestBody AuthorRequestDto authorRequestDto, @PathVariable UUID id){
        return new ResponseEntity<>((modelMapper.map(authorService.updateAuthor(id,authorRequestDto),AuthorResponseDto.class))
                ,HttpStatus.OK);
    }
    @GetMapping("/{id}")
public ResponseEntity<AuthorResponseDto> viewAuthor(@PathVariable UUID id){
        return new ResponseEntity<>((modelMapper.map(authorService.viewAuthor(id),AuthorResponseDto.class))
                ,HttpStatus.FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable UUID id){
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Author deleted");
    }
}
