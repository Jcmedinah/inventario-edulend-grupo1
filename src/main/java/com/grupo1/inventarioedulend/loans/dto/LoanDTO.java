package com.grupo1.inventarioedulend.loans.dto;

import java.time.LocalDate;
import com.grupo1.inventarioedulend.users.dto.UserDTO;
import com.grupo1.inventarioedulend.articles.dto.ArticleDTO;

public record LoanDTO(
    int loan_id,
    UserDTO user,
    ArticleDTO item,
    LocalDate loan_date,
    LocalDate due_date,
    LocalDate return_date,
    boolean status
) {}
