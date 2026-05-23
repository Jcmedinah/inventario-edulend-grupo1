package com.grupo1.inventarioedulend.categories.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo1.inventarioedulend.categories.dto.CategoryCreateDTO;
import com.grupo1.inventarioedulend.categories.dto.CategoryDTO;
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
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryCreateDTO request) {
        CategoryDTO created = categoryService.createCategory(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/by-id")
    public ResponseEntity<CategoryDTO> getCategoryById(@RequestParam int category_id) {
        return categoryService.getCategoryById(category_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<CategoryDTO> getCategoryByName(@RequestParam String name) {
        return categoryService.getCategoryByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id, @RequestBody CategoryCreateDTO request) {
        CategoryDTO updated = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}