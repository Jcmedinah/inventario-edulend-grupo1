package com.grupo1.inventarioedulend.articles.dto;

public record ArticleCreateDTO(
    String name,
    String description,
    int categoryId,
    int quantity_total,
    int quantity_damaged
) {}
