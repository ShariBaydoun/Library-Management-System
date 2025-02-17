package com.example.LibraryManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="book_title")
    private String title;
    @Column(name="book_isbn")
    private String isbn;
    @Column(name="author_name")
    private String authorname;
    @Column(name="book_category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name="book_availability")
    @Enumerated(EnumType.STRING)
    private Availability availability;
    @OneToMany(mappedBy ="book")
    private List<BookAuthor> bookAuthors;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions=new ArrayList<>();


}
