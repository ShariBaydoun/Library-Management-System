package com.example.LibraryManagementSystem.controller;

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
    @PostMapping("/{borrowerId}/{bookId}/{borrowerEmail}")
    public ResponseEntity<TransactionResponseDto> borrowBook(@PathVariable UUID borrowerId,@PathVariable UUID bookId
            ,@PathVariable String borrowerEmail){
        Transaction transaction = transactionService.borrowBook(borrowerId,bookId,borrowerEmail);
        TransactionResponseDto transactionResponseDto = modelMapper.map(transaction, TransactionResponseDto.class);
        return new ResponseEntity<>(transactionResponseDto,
                HttpStatus.CREATED);
    }
    @PutMapping("/{borrowerId}/{bookId}")
    public ResponseEntity<TransactionResponseDto> returnBook(@PathVariable UUID borrowerId,@PathVariable UUID bookId){
        Transaction transaction=transactionService.returnBook(borrowerId,bookId);
        TransactionResponseDto transactionResponseDto= modelMapper.map(transaction, TransactionResponseDto.class);
        return new ResponseEntity<>(transactionResponseDto,HttpStatus.OK);
    }
}
