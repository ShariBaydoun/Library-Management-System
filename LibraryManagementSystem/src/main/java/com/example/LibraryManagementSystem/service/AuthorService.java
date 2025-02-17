package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.AuthorRequestDto;
import com.example.LibraryManagementSystem.model.Author;

import java.util.UUID;

public interface AuthorService {
    Author addAuthor(AuthorRequestDto authorRequestDto);
    Author updateAuthor(UUID id,AuthorRequestDto authorRequestDto);
    Author viewAuthor(UUID id);
    void deleteAuthor(UUID id);


}
