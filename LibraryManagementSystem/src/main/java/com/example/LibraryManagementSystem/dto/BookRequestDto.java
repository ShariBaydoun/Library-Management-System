package com.example.LibraryManagementSystem.dto;

import com.example.LibraryManagementSystem.model.Availability;
import com.example.LibraryManagementSystem.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BookRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String isbn;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Availability availability;
    @NotNull
    private BigDecimal extraDaysRentalPrice;
    @NotNull
    private BigDecimal insuranceFees;
    @NotNull
    private BigDecimal price;


}
