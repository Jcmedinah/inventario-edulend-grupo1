package com.grupo1.inventarioedulend.articles.dto;

import com.grupo1.inventarioedulend.categories.dto.CategoryDTO;

public record ArticleDTO(
    int itemId,
    String name,
    String description,
    CategoryDTO category,
    int quantity_available
) {}
