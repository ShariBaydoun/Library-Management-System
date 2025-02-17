package com.example.LibraryManagementSystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="BookAuthor")
@Getter
@Setter

public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="Book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name="Author_id")
    private Author author;





}
