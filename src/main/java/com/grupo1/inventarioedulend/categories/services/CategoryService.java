package com.grupo1.inventarioedulend.categories.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.grupo1.inventarioedulend.categories.datasource.CategoryRepository;
import com.grupo1.inventarioedulend.categories.dto.CategoryCreateDTO;
import com.grupo1.inventarioedulend.categories.dto.CategoryDTO;
import com.grupo1.inventarioedulend.categories.models.Category;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO createCategory(CategoryCreateDTO request) {
        // Validar que no exista la categoría por nombre antes de crearla
        categoryRepository.findByCategoryName(request.categoryName())
            .ifPresent(c -> { 
                throw new RuntimeException("La categoría ya existe con el nombre: " + request.categoryName()); 
            });
            
        Category category = convertToEntity(request);
        return convertToDTO(categoryRepository.save(category));
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getCategoryById(int id) {
        return categoryRepository.findById(id).map(this::convertToDTO);
    }

    public Optional<CategoryDTO> getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name).map(this::convertToDTO);
    }

    public CategoryDTO updateCategory(int id, CategoryCreateDTO request) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        
        existing.setCategoryName(request.categoryName());
        
        return convertToDTO(categoryRepository.save(existing));
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    // --- Mapeo Manual ---

    public CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(
            category.getCategoryId(),
            category.getCategoryName()
        );
    }

    public Category convertToEntity(CategoryCreateDTO dto) {
        Category category = new Category();
        category.setCategoryName(dto.categoryName());
        return category;
    }
}