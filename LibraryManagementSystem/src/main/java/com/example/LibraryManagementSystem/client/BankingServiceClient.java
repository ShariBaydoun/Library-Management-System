package com.example.LibraryManagementSystem.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "transactionServicee", url = "http://localhost:8081/api/transactions")
public interface BankingServiceClient {
    @PostMapping("/Book")
    String processBookTransaction(@RequestParam("cardNumber") String cardNumber, @RequestParam("amount") BigDecimal amount);

    @PostMapping("/refund")
    String processRefund(@RequestParam("cardNumber") String cardNumber, @RequestParam("amount") BigDecimal amount);

}

