package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.dto.AuthorRequestDto;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.service.AuthorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
public class AuthorServiceImpl  implements AuthorService {
    private final AuthorRepository authorRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Author addAuthor(AuthorRequestDto authorRequestDto) {

        return authorRepository.save(modelMapper.map(authorRequestDto,Author.class));
    }

    @Override
    public Author updateAuthor(UUID authorId,AuthorRequestDto authorRequestDto) {
        Author author=authorRepository.findById(authorId).orElseThrow(()->new RuntimeException("Author not found"));
        // Update fields if they are not null
        if (authorRequestDto.getBiography() != null) {
            author.setBiography(authorRequestDto.getBiography());
        }
        if (authorRequestDto.getName() != null) {
            author.setName(authorRequestDto.getName());
        }
        return authorRepository.save(author);
    }

    @Override
    public Author viewAuthor(UUID authorId) {
        return authorRepository.findById(authorId).orElseThrow(()->new RuntimeException("Author not found"));
    }

    @Override
    public void deleteAuthor(UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepository.delete(author);
    }


}
