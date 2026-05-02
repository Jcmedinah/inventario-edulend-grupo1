package com.grupo1.inventarioedulend.loans.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.grupo1.inventarioedulend.users.models.User;
import com.grupo1.inventarioedulend.articles.models.Article;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "loans")
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loan_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Article item;

    @Column(nullable = false)
    private LocalDate loan_date;

    @Column(nullable = false)
    private LocalDate due_date;

    private LocalDate return_date;

    @Column(nullable = false)
    private boolean status = true; 

    private LocalDateTime updated_at;

    @PrePersist
    public void prePersist() {
        if (loan_date == null) {
            loan_date = LocalDate.now();
        }
        updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updated_at = LocalDateTime.now();
    }
}