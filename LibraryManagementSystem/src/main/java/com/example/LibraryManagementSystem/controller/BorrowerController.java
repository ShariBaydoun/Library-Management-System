package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.BookResponseDto;
import com.example.LibraryManagementSystem.dto.BorrowerRequestDto;
import com.example.LibraryManagementSystem.dto.BorrowerResponseDto;
import com.example.LibraryManagementSystem.service.BorrowerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/borrower")
@AllArgsConstructor
public class BorrowerController {
    private final BorrowerService borrowerService;
    private ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<BorrowerResponseDto> addBorrower(@RequestBody BorrowerRequestDto borrowerRequestDto){
        return new ResponseEntity<>((modelMapper.map(borrowerService.addBorrower(borrowerRequestDto),BorrowerResponseDto.class))
                ,HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public  ResponseEntity<BorrowerResponseDto> updateBorrower(@PathVariable UUID id,@RequestBody BorrowerRequestDto borrowerRequestDto){
        return new ResponseEntity<>((modelMapper.map(borrowerService.updateBorrower(id,borrowerRequestDto),BorrowerResponseDto.class))
        ,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BorrowerResponseDto> viewBorrower(@PathVariable UUID id){
        return new ResponseEntity<>((modelMapper.map(borrowerService.viewBorrower(id),BorrowerResponseDto.class)),
                HttpStatus.FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBorrower(@PathVariable UUID id){
        borrowerService.deleteBorrower(id);
        return ResponseEntity.ok("Borrower deleted");
    }

}
