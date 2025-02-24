package com.example.LibraryManagementSystem.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class LimitConfigService {
    @Value("${borrower.transaction.limit}")
    private int transactionLimit;

    public int getTransactionLimit() {
        return transactionLimit;
    }
}
