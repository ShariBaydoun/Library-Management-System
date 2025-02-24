package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.dto.BorrowerRequestDto;
import com.example.LibraryManagementSystem.model.Borrower;
import com.example.LibraryManagementSystem.repository.BorrowerRepository;
import com.example.LibraryManagementSystem.service.BorrowerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
public class BorrowerServiceImpl implements BorrowerService {
    private final BorrowerRepository borrowerRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Borrower addBorrower(BorrowerRequestDto borrowerRequestDto) {
        return borrowerRepository.save(modelMapper.map(borrowerRequestDto,Borrower.class));
    }

    @Override
    public Borrower updateBorrower(UUID borrowerId,BorrowerRequestDto borrowerRequestDto) {
        Borrower borrower=borrowerRepository.findById(borrowerId)
                .orElseThrow(()->new RuntimeException("Borrower not found"));
        if (borrowerRequestDto.getName() !=null){
            borrower.setName(borrowerRequestDto.getName());
        }
        if (borrowerRequestDto.getPhoneNumber() !=null){
            borrower.setPhoneNumber(borrowerRequestDto.getPhoneNumber());
        }
        if (borrowerRequestDto.getEmail() !=null){
            borrower.setEmail(borrowerRequestDto.getEmail());
        }

        return borrowerRepository.save(borrower) ;
    }

    @Override
    public Borrower viewBorrower(UUID borrowerId) {
       return borrowerRepository.findById(borrowerId)
                .orElseThrow(()->new RuntimeException("Borrower not found"));

    }

    @Override
    public void deleteBorrower(UUID borrowerId) {
       Borrower borrower= borrowerRepository.findById(borrowerId)
                .orElseThrow(()->new RuntimeException("Borrower not found"));
       borrowerRepository.delete(borrower);

    }
}
