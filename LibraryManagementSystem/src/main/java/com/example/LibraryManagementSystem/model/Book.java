package com.example.LibraryManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Column(name="title",nullable = false)
    private String title;
    @NotNull
    @Column(name="isbn",unique = true, nullable = false)
    private String isbn;
    @NotNull
    @Column(name="authorName",nullable = false)
    private String authorName;
    @NotNull
    @Column(name="category",nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotNull
    @Column(name="availability",nullable = false)
    @Enumerated(EnumType.STRING)
    private Availability availability;
    @OneToMany(mappedBy ="book")
    private List<BookAuthor> bookAuthors;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions=new ArrayList<>();


}
