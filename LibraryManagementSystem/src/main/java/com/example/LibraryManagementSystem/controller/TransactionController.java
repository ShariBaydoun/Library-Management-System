package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.BorrowBookRequestDto;
import
        com.example.LibraryManagementSystem.dto.TransactionResponseDto;
import com.example.LibraryManagementSystem.model.Transaction;
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
    @PostMapping
    public ResponseEntity<TransactionResponseDto> borrowBook(@RequestBody BorrowBookRequestDto borrowBookRequestDto){
        Transaction transaction = transactionService.borrowBook(borrowBookRequestDto);
        TransactionResponseDto transactionResponseDto = modelMapper.map(transaction, TransactionResponseDto.class);
        return new ResponseEntity<>(transactionResponseDto,
                HttpStatus.CREATED);
    }
    @PostMapping("/return")
    public ResponseEntity<TransactionResponseDto> returnBook(@RequestBody BorrowBookRequestDto borrowBookRequestDto){
        Transaction transaction=transactionService.returnBook(borrowBookRequestDto);
        TransactionResponseDto transactionResponseDto= modelMapper.map(transaction, TransactionResponseDto.class);
        return new ResponseEntity<>(transactionResponseDto,HttpStatus.OK);
    }
}
