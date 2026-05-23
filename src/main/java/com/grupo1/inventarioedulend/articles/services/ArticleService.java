package com.grupo1.inventarioedulend.articles.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.grupo1.inventarioedulend.articles.datasource.ArticleRepository;
import com.grupo1.inventarioedulend.articles.dto.ArticleCreateDTO;
import com.grupo1.inventarioedulend.articles.dto.ArticleDTO;
import com.grupo1.inventarioedulend.articles.models.Article;
import com.grupo1.inventarioedulend.categories.datasource.CategoryRepository;
import com.grupo1.inventarioedulend.categories.dto.CategoryDTO;
import com.grupo1.inventarioedulend.categories.models.Category;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    public ArticleDTO create(ArticleCreateDTO request) {
        Article article = convertToEntity(request);
        return convertToDTO(articleRepository.save(article));
    }

    public List<ArticleDTO> getAll() {
        return articleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ArticleDTO> getById(int id) {
        return articleRepository.findById(id).map(this::convertToDTO);
    }

    public List<ArticleDTO> getByCategory(int category_id) {
        return articleRepository.findByCategory_CategoryId(category_id).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ArticleDTO update(int id, ArticleCreateDTO request) {
        Article existing = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        existing.setName(request.name());
        existing.setDescription(request.description());
        existing.setCategory(category);
        
        int borrowed = existing.getQuantity_total() - existing.getQuantity_available() - existing.getQuantity_damaged();
        existing.setQuantity_total(request.quantity_total());
        existing.setQuantity_damaged(request.quantity_damaged());
        existing.setQuantity_available(request.quantity_total() - request.quantity_damaged() - borrowed);

        return convertToDTO(articleRepository.save(existing));
    }

    public void delete(int id) {
        articleRepository.deleteById(id);
    }

    // --- Mapeo Manual ---

    public ArticleDTO convertToDTO(Article article) {
        CategoryDTO categoryDTO = new CategoryDTO(
                article.getCategory().getCategoryId(),
                article.getCategory().getCategoryName()
        );
        return new ArticleDTO(
                article.getItemId(),
                article.getName(),
                article.getDescription(),
                categoryDTO,
                article.getQuantity_available(),
                article.getQuantity_total(),
                article.getQuantity_damaged()
        );
    }

    public Article convertToEntity(ArticleCreateDTO dto) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        
        Article article = new Article();
        article.setName(dto.name());
        article.setDescription(dto.description());
        article.setCategory(category);
        article.setQuantity_total(dto.quantity_total());
        article.setQuantity_damaged(dto.quantity_damaged());
        article.setQuantity_available(dto.quantity_total() - dto.quantity_damaged());
        return article;
    }
}