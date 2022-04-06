package com.reply.insideproject.repository;

import com.reply.insideproject.model.Article;
import com.reply.insideproject.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleRepositoryTest {

    @Mock
    private ArticleRepository articleRepository;

    private Article article;

    @BeforeEach
    private void init() {
        article = new Article(1L, new Category(1L, "Food"), "Pasta", "La pasta è buona");
    }

    @Test
    void findByName() {
        when(articleRepository.findByName(any(String.class))).thenReturn(Optional.of(article));
        assertNotNull(articleRepository.findByName(article.getName()));
        verify(articleRepository).findByName(any(String.class));
    }

}