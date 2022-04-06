package com.reply.insideproject.service;

import com.reply.insideproject.dto.CommentDto;
import com.reply.insideproject.model.Article;
import com.reply.insideproject.model.Category;
import com.reply.insideproject.model.Comment;
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
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ArticleServiceImpl articleService;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Article article;

    private Comment comment;

    private CommentDto commentDto;

    @BeforeEach
    private void init() {
        article = new Article(1L, new Category(1L, "Food"), "Pasta", "La pasta è buona");

        commentDto = new CommentDto("jack", "Preferisco la carne");

        comment = new Comment(1L, new Article(), commentDto.getNickname(), commentDto.getBody(), new Date());
    }

    @Test
    void create() {

        when(articleService.getById(any(Long.class))).thenReturn(article);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        assertDoesNotThrow(() -> commentService.create(article.getId(), commentDto));

        verify(articleService).getById(any(Long.class));
        verify(commentRepository).save(any(Comment.class));

    }

    @Test
    void getById() {

        doNothing().when(articleService).checkArticle(any(Long.class));
        when(commentRepository.findById(any(Long.class))).thenReturn(Optional.of(comment));

        assertDoesNotThrow(() -> commentService.getById(article.getId(), comment.getId()));

        verify(articleService).checkArticle(any(Long.class));
        verify(commentRepository).findById(any(Long.class));

    }

    @Test
    void getByPage() {

        doNothing().when(articleService).checkArticle(any(Long.class));
        when(commentRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        assertDoesNotThrow(() -> commentService.getByPage(article.getId(), 0, 20));

        verify(articleService).checkArticle(any(Long.class));
        verify(commentRepository).findAll(any(PageRequest.class));

    }

    @Test
    void update() {

        when(commentRepository.findById(any(Long.class))).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        assertDoesNotThrow(() -> commentService.update(article.getId(), comment.getId(), commentDto));

        verify(commentRepository).findById(any(Long.class));
        verify(commentRepository).save(any(Comment.class));

    }

    @Test
    void delete() {

        when(commentRepository.findById(any(Long.class))).thenReturn(Optional.of(comment));
        doNothing().when(commentRepository).delete(any(Comment.class));

        assertDoesNotThrow(() -> commentService.delete(article.getId(), comment.getId()));

        verify(commentRepository).findById(any(Long.class));
        verify(commentRepository).delete(any(Comment.class));

    }

    @Test
    void deleteAllByIdArticle() {

        doNothing().when(commentRepository).deleteAllByArticle(any(Long.class));
        assertDoesNotThrow(() -> commentService.deleteAllByIdArticle(article.getId()));
        verify(commentRepository).deleteAllByArticle(any(Long.class));

    }

}