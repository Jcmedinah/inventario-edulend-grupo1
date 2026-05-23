package com.grupo1.inventarioedulend.loans.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.grupo1.inventarioedulend.loans.datasource.LoanRepository;
import com.grupo1.inventarioedulend.loans.dto.LoanCreateDTO;
import com.grupo1.inventarioedulend.loans.dto.LoanDTO;
import com.grupo1.inventarioedulend.loans.models.Loan;
import com.grupo1.inventarioedulend.users.datasource.UserRepository;
import com.grupo1.inventarioedulend.users.models.User;
import com.grupo1.inventarioedulend.articles.datasource.ArticleRepository;
import com.grupo1.inventarioedulend.articles.models.Article;
import com.grupo1.inventarioedulend.users.services.UserService;
import com.grupo1.inventarioedulend.articles.services.ArticleService;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final ArticleService articleService;

    public LoanService(LoanRepository loanRepository, UserRepository userRepository, 
                       ArticleRepository articleRepository, UserService userService, 
                       ArticleService articleService) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.articleService = articleService;
    }

    public LoanDTO create(LoanCreateDTO request) {
        Loan loan = convertToEntity(request);
        return convertToDTO(loanRepository.save(loan));
    }

    public List<LoanDTO> getAll() {
        return loanRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<LoanDTO> getById(int id) {
        return loanRepository.findById(id).map(this::convertToDTO);
    }

    public List<LoanDTO> getByUser(int user_id) {
        return loanRepository.findByUser_UserId(user_id).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<LoanDTO> getByItem(int item_id) {
        return loanRepository.findByItem_ItemId(item_id).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<LoanDTO> getByStatus(boolean status) {
        return loanRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LoanDTO update(int id, LoanCreateDTO request) {
        Loan existing = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                
        Article article = articleRepository.findById(request.itemId())
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        existing.setUser(user);
        existing.setItem(article);
        existing.setLoan_date(request.loan_date());
        existing.setDue_date(request.due_date());
        existing.setReturn_date(request.return_date());
        existing.setStatus(request.status());

        return convertToDTO(loanRepository.save(existing));
    }

    public void delete(int id) {
        loanRepository.deleteById(id);
    }

    // --- Mapeo Manual ---

    public LoanDTO convertToDTO(Loan loan) {
        return new LoanDTO(
            loan.getLoan_id(),
            userService.convertToDTO(loan.getUser()),
            articleService.convertToDTO(loan.getItem()),
            loan.getLoan_date(),
            loan.getDue_date(),
            loan.getReturn_date(),
            loan.isStatus()
        );
    }

    public Loan convertToEntity(LoanCreateDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                
        Article article = articleRepository.findById(dto.itemId())
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setItem(article);
        loan.setLoan_date(dto.loan_date());
        loan.setDue_date(dto.due_date());
        loan.setReturn_date(dto.return_date());
        loan.setStatus(dto.status());
        return loan;
    }
}