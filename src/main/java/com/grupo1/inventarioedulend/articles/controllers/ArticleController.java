package com.grupo1.inventarioedulend.articles.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo1.inventarioedulend.articles.dto.ArticleCreateDTO;
import com.grupo1.inventarioedulend.articles.dto.ArticleDTO;
import com.grupo1.inventarioedulend.articles.services.ArticleService; 

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "*")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> create(@RequestBody ArticleCreateDTO request) {
        ArticleDTO created = articleService.create(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAll() {
        return ResponseEntity.ok(articleService.getAll());
    }

    @GetMapping("/by-id")
    public ResponseEntity<ArticleDTO> getById(@RequestParam int article_id) {
        return articleService.getById(article_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<ArticleDTO>> getByCategory(@RequestParam int category_id) {
        List<ArticleDTO> articles = articleService.getByCategory(category_id);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> update(@PathVariable int id, @RequestBody ArticleCreateDTO request) {
        ArticleDTO updated = articleService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        articleService.delete(id);
        return ResponseEntity.ok("Artículo eliminado correctamente.");
    }
}