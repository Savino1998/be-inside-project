package com.reply.insideproject.repository;

import com.reply.insideproject.model.Article;
import com.reply.insideproject.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommentRepositoryTest {

    @Mock
    private CommentRepository commentRepository;

    private Comment comment;

    @BeforeEach
    private void init() {
        comment = new Comment(1L, new Article(), "jack", "Preferisco la carne", new Date());
    }

    @Test
    void deleteAllByArticle() {
        doNothing().when(commentRepository).deleteAllByArticle(any(Long.class));
        assertDoesNotThrow(() -> commentRepository.deleteAllByArticle(comment.getId()));
        verify(commentRepository).deleteAllByArticle(any(Long.class));
    }

}