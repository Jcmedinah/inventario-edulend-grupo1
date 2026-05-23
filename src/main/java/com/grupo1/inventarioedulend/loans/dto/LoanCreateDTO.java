package com.grupo1.inventarioedulend.loans.dto;

import java.time.LocalDate;

public record LoanCreateDTO(
    int userId,
    int itemId,
    LocalDate loan_date,
    LocalDate due_date,
    LocalDate return_date,
    boolean status
) {}
