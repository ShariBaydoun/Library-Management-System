package com.example.LibraryManagementSystem.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class ApiServiceImpl {

    private final RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchBookData(String isbn) {
        String apiUrl = "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

            // Check if response is empty
            if (response.getBody() == null || response.getBody().isBlank()) {
                return "No data found for ISBN: " + isbn;
            }

            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Book not found for ISBN: " + isbn);
        } catch (ResourceAccessException e) {
            throw new RuntimeException("Failed to connect to Open Library API.");
        } catch (Exception e) {
            throw new RuntimeException( "An unexpected error occurred: " + e.getMessage());
        }
    }
}
