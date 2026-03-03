package com.grupo1.inventarioedulend.articles.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.grupo1.inventarioedulend.articles.datasource.ArticleRepository;
import com.grupo1.inventarioedulend.articles.models.Article;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    // Constructor manual para mantener el estilo del equipo
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article create(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> getById(int id) {
        return articleRepository.findById(id);
    }

    public List<Article> getByCategory(int category_id) {
        // Usamos el método que definimos en el ArticleRepository
        return articleRepository.findByCategory_CategoryId(category_id);
    }

    public Article update(int id, Article articleData) {
        Article existing = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        // Seteamos los datos manteniendo la estructura que ya tenías
        existing.setName(articleData.getName());
        existing.setDescription(articleData.getDescription());
        existing.setCategory(articleData.getCategory());
        existing.setQuantity_available(articleData.getQuantity_available());

        return articleRepository.save(existing);
    }

    public void delete(int id) {
        articleRepository.deleteById(id);
    }
}