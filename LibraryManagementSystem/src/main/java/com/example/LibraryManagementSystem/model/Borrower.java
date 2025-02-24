package com.example.LibraryManagementSystem.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Column(name="name",nullable = false)
    private String name;
    @NotNull
    @Column(name="email",nullable = false)
    @Email
    private String email;
    @NotNull
    @Column(name="phone_number",nullable = false)
    private String phoneNumber;
    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions=new ArrayList<>();

}
