package com.grupo1.inventarioedulend.loans.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.grupo1.inventarioedulend.loans.dto.LoanCreateDTO;
import com.grupo1.inventarioedulend.loans.dto.LoanDTO;
import com.grupo1.inventarioedulend.loans.services.LoanService;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public LoanDTO create(@RequestBody LoanCreateDTO request) {
        return loanService.create(request);
    }

    @GetMapping
    public List<LoanDTO> getAll() {
        return loanService.getAll();
    }

    @GetMapping("/by-id")
    public LoanDTO getById(@RequestParam int loan_id) {
        return loanService.getById(loan_id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
    }

    @GetMapping("/by-user")
    public List<LoanDTO> getByUser(@RequestParam int user_id) {
        return loanService.getByUser(user_id);
    }

    @GetMapping("/item/{item_id}")
    public List<LoanDTO> getByItem(@PathVariable int item_id) {
        return loanService.getByItem(item_id);
    }

    @GetMapping("/status/{status}")
    public List<LoanDTO> getByStatus(@PathVariable boolean status) {
        return loanService.getByStatus(status);
    }

    @PutMapping("/{id}")
    public LoanDTO update(@PathVariable int id, @RequestBody LoanCreateDTO request) {
        return loanService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        loanService.delete(id);
        return "Préstamo eliminado correctamente";
    }
}