package com.grupo1.inventarioedulend.categories.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.grupo1.inventarioedulend.categories.datasource.CategoryRepository;
import com.grupo1.inventarioedulend.categories.models.Category;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        // Validar que no exista la categoría por nombre antes de crearla
        categoryRepository.findByCategoryName(category.getCategoryName())
            .ifPresent(c -> { 
                throw new RuntimeException("La categoría ya existe con el nombre: " + category.getCategoryName()); 
            });
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    public Category updateCategory(int id, Category categoryData) {
        // Aunque el repo permite save directo, es mejor práctica buscar primero
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        
        existing.setCategoryName(categoryData.getCategoryName());
        // El updated_at se actualizará solo por el @PreUpdate que pusimos en el modelo
        
        return categoryRepository.save(existing);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}