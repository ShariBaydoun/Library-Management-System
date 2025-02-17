package com.example.LibraryManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
@Entity
@Table(name="Transaction")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
private UUID id;
    @Column(name="borrow_date")
private LocalDate borrowdate;
@Column(name="return_date")
private LocalDate returndate;
@Column(name="transaction_status")
@Enumerated(EnumType.STRING)
private Status status;
@ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
@ManyToOne
    @JoinColumn(name="borrower_id",nullable = false)
    private Borrower borrower;



}
