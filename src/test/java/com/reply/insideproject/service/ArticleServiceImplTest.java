package com.reply.insideproject.service;

import com.reply.insideproject.dto.ArticleDto;
import com.reply.insideproject.model.Article;
import com.reply.insideproject.model.Category;
import com.reply.insideproject.repository.ArticleRepository;
import com.reply.insideproject.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    private Article article;

    private ArticleDto articleDto;

    private Category category;

    @BeforeEach
    private void init() {

        category = new Category(1L, "Food");

        articleDto = new ArticleDto(category.getId(), "La pasta è buona", "Pasta");

        article = new Article(1L, category, articleDto.getName(), articleDto.getBody());

    }

    @Test
    void create() {

        when(articleRepository.findByName(any(String.class))).thenReturn(Optional.empty());
        when(categoryService.getById(any(Long.class))).thenReturn(category);
        when(articleRepository.save(any(Article.class))).thenReturn(article);

        assertDoesNotThrow(() -> articleService.create(articleDto));

        verify(articleRepository).save(any(Article.class));
        verify(articleRepository).findByName(any(String.class));
        verify(categoryService).getById(any(Long.class));

    }

    @Test
    void getById() {

        when(articleRepository.findById(any(Long.class))).thenReturn(Optional.of(article));
        assertDoesNotThrow(() -> articleService.getById(article.getId()));
        verify(articleRepository).findById(any(Long.class));

    }

    @Test
    void getByPage() {

        when(articleRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertDoesNotThrow(() -> articleService.getByPage(0, 20));
        verify(articleRepository).findAll(any(PageRequest.class));

    }

    @Test
    void update() {

        when(articleRepository.findById(any(Long.class))).thenReturn(Optional.of(article));
        when(categoryService.getById(any(Long.class))).thenReturn(category);
        when(articleRepository.save(any(Article.class))).thenReturn(article);

        assertDoesNotThrow(() -> articleService.update(article.getId(), articleDto));

        verify(articleRepository).findById(any(Long.class));
        verify(categoryService).getById(any(Long.class));
        verify(articleRepository).save(any(Article.class));

    }

    @Test
    void delete() {

        when(articleRepository.findById(any(Long.class))).thenReturn(Optional.of(article));
        doNothing().when(articleRepository).delete(any(Article.class));
        doNothing().when(commentRepository).deleteAllByArticle(any(Long.class));

        assertDoesNotThrow(() -> articleService.delete(article.getId()));

        verify(articleRepository).findById(any(Long.class));
        verify(articleRepository).delete(any(Article.class));
        verify(commentRepository).deleteAllByArticle(any(Long.class));

    }

    @Test
    void checkArticle() {

        when(articleRepository.existsById(any(Long.class))).thenReturn(true);
        assertDoesNotThrow(() -> articleService.checkArticle(article.getId()));
        verify(articleRepository).existsById(any(Long.class));

    }

}