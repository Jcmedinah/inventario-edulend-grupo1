package com.grupo1.inventarioedulend.loans.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grupo1.inventarioedulend.loans.models.Loan;
import com.grupo1.inventarioedulend.loans.services.LoanService;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public Loan create(@RequestBody Loan loan) {
        return loanService.create(loan);
    }

    @GetMapping
    public List<Loan> getAll() {
        return loanService.getAll();
    }

    @GetMapping("/by-id")
    public Loan getById(@RequestParam int loan_id) {
        return loanService.getById(loan_id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
    }

    @GetMapping("/by-user")
    public List<Loan> getByUser(@RequestParam int user_id) {
        return loanService.getByUser(user_id);
    }

    @GetMapping("/item/{item_id}")
    public List<Loan> getByItem(@PathVariable int item_id) {
        return loanService.getByItem(item_id);
    }

    @GetMapping("/status/{status}")
    public List<Loan> getByStatus(@PathVariable boolean status) {
        return loanService.getByStatus(status);
    }

    @PutMapping("/{id}")
    public Loan update(@PathVariable int id, @RequestBody Loan loan) {
        return loanService.update(id, loan);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        loanService.delete(id);
        return "Préstamo eliminado correctamente";
    }
}