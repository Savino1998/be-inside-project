package com.reply.insideproject.service;

import com.reply.insideproject.dto.CommentDto;
import com.reply.insideproject.model.Article;
import com.reply.insideproject.model.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {

    Comment create(Long idArticle, CommentDto comment);

    Comment getById(Long idArticle, Long id);

    Page<Comment> getByPage(Long idArticle, Integer offset, Integer size);

    Comment update(Long idArticle, Long id, CommentDto comment);

    void delete(Long idArticle, Long id);

    void deleteAllByIdArticle(Article article);

}
