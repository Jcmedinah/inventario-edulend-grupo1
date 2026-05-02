package com.grupo1.inventarioedulend.loans.datasource;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo1.inventarioedulend.loans.models.Loan;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByUser_UserId(int userId);
    List<Loan> findByItem_ItemId(int itemId);
    List<Loan> findByStatus(boolean status);
}