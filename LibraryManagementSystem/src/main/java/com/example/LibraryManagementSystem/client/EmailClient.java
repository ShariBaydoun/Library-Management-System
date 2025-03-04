package com.example.LibraryManagementSystem.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "emailService", url = "http://localhost:8082/api/email")

public interface EmailClient {
    @PostMapping("/send")
    String sendEmail(@RequestParam String to,
                     @RequestParam String subject,
                     @RequestParam String text);

}

