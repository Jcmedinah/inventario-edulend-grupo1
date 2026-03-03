package com.grupo1.inventarioedulend.articles.controllers;

import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo1.inventarioedulend.articles.models.Article;
import com.grupo1.inventarioedulend.articles.services.ArticleService; // Tu nuevo nombre

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "*")
public class ArticleController {

    private final ArticleService articleService;

    //@Autowired
    public ArticleController(ArticleService articleService) {
        // Al igual que con Loans, inyectamos directamente el Service
        this.articleService = articleService;
    }

    // ✅ Crear artículo
    @PostMapping
    public ResponseEntity<Article> create(@RequestBody Article article) {
        Article created = articleService.create(article);
        return ResponseEntity.ok(created);
    }

    // ✅ Obtener todos los artículos
    @GetMapping
    public ResponseEntity<List<Article>> getAll() {
        return ResponseEntity.ok(articleService.getAll());
    }

    // ✅ Obtener artículo por ID
    @GetMapping("/by-id")
    public ResponseEntity<Article> getById(@RequestParam int article_id) {
        return articleService.getById(article_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Obtener artículos por categoría
    @GetMapping("/by-category")
    public ResponseEntity<List<Article>> getByCategory(@RequestParam int category_id) {
        List<Article> articles = articleService.getByCategory(category_id);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    // ✅ Actualizar artículo
    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable int id, @RequestBody Article article) {
        Article updated = articleService.update(id, article);
        return ResponseEntity.ok(updated);
    }

    // ✅ Eliminar artículo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        articleService.delete(id);
        return ResponseEntity.ok("Artículo eliminado correctamente.");
    }
}