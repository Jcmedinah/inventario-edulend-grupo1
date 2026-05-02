package com.grupo1.inventarioedulend.articles.datasource;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo1.inventarioedulend.articles.models.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByCategory_CategoryId(int categoryId);
}