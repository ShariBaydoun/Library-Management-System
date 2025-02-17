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
@Table(name="Borrower")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="borrower_name")
    private String name;
    @Column(name="borrower_email")
    private String email;
    @Column(name="borrower_phone_number")
    private String phonenumber;
    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions=new ArrayList<>();

}
