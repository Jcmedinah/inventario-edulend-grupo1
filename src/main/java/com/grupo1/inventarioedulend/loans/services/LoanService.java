package com.grupo1.inventarioedulend.loans.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.grupo1.inventarioedulend.loans.datasource.LoanRepository;
import com.grupo1.inventarioedulend.loans.models.Loan;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan create(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getById(int id) {
        return loanRepository.findById(id);
    }

    public List<Loan> getByUser(int user_id) {
        return loanRepository.findByUser_UserId(user_id);
    }

    public List<Loan> getByItem(int item_id) {
        return loanRepository.findByItem_ItemId(item_id);
    }

    public List<Loan> getByStatus(boolean status) {
        return loanRepository.findByStatus(status);
    }

    public Loan update(int id, Loan loanData) {
        Loan existing = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        existing.setUser(loanData.getUser());
        existing.setItem(loanData.getItem());
        existing.setLoan_date(loanData.getLoan_date());
        existing.setDue_date(loanData.getDue_date());
        existing.setReturn_date(loanData.getReturn_date());
        existing.setStatus(loanData.isStatus());

        return loanRepository.save(existing);
    }

    public void delete(int id) {
        loanRepository.deleteById(id);
    }
}