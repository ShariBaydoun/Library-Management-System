package com.example.LibraryManagementSystem.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@TypeDef(name = "json", typeClass = JsonStringType.class)
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
    @Type(type = "jsonb")
    @Column(name = "book_properties", columnDefinition = "jsonb")  // Ensure DB stores it as JSON
    private Map<String, String> bookProperties;
    @NotNull
    @Column(name="price",nullable = false)
    private BigDecimal price;
    @OneToMany(mappedBy ="book")
    private List<BookAuthor> bookAuthors;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions=new ArrayList<>();

}
