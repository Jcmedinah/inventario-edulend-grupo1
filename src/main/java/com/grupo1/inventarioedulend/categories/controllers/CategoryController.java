package com.grupo1.inventarioedulend.categories.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo1.inventarioedulend.categories.models.Category;
import com.grupo1.inventarioedulend.categories.services.CategoryService; 

@RestController
@RequestMapping("/api/categories") 
@CrossOrigin(origins = "*") 
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestParam String name) {
        Category category = new Category();
        category.setCategoryName(name);
        Category created = categoryService.createCategory(category);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/by-id")
    public ResponseEntity<Category> getCategoryById(@RequestParam int category_id) {
        return categoryService.getCategoryById(category_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Category> getCategoryByName(@RequestParam String name) {
        return categoryService.getCategoryByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestParam String name) {
        // Creamos un objeto temporal con los nuevos datos
        Category categoryData = new Category();
        categoryData.setCategoryName(name);
        
        Category updated = categoryService.updateCategory(id, categoryData);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}