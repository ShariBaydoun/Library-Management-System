package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.TransactionResponseDto;
import com.example.LibraryManagementSystem.service.TransactionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/transaction")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private ModelMapper modelMapper;
    @PostMapping("/{borrowerId}/{bookId}")
    public ResponseEntity<TransactionResponseDto> borrowBook(@PathVariable UUID borrowerId,@PathVariable UUID bookId){
        return new ResponseEntity<>((modelMapper.map(transactionService.borrowBook(borrowerId,bookId), TransactionResponseDto.class)),
                HttpStatus.CREATED);
    }
    @PutMapping("/{borrowerId}/{bookId}")
    public ResponseEntity<TransactionResponseDto> returnBook(@PathVariable UUID borrowerId,@PathVariable UUID bookId){
        return new ResponseEntity<>((modelMapper.map(transactionService.returnBook(borrowerId,bookId), TransactionResponseDto.class))
        ,HttpStatus.OK);
    }
}
