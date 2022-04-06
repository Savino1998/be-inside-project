package com.reply.insideproject.service.impl;

import com.reply.insideproject.dto.CommentDto;
import com.reply.insideproject.exception.NotFoundException;
import com.reply.insideproject.model.Comment;
import com.reply.insideproject.repository.CommentRepository;
import com.reply.insideproject.service.ArticleService;
import com.reply.insideproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleService articleService;

    @Override
    public Comment create(Long idArticle, CommentDto comment) {
        return commentRepository.save(new Comment(articleService.getById(idArticle), comment.getNickname(), comment.getBody()));
    }

    @Override
    public Comment getById(Long idArticle, Long id) {
        articleService.checkArticle(idArticle);
        return commentRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(id, Comment.class);
        });
    }

    @Override
    public Page<Comment> getByPage(Long idArticle, Integer offset, Integer size) {
        articleService.checkArticle(idArticle);
        return commentRepository.findAll(PageRequest.of(offset, size));
    }

    @Override
    public Comment update(Long idArticle, Long id, CommentDto comment) {
        Comment newComment = getById(idArticle, id);
        newComment.setNickname(comment.getNickname());
        newComment.setBody(comment.getBody());
        newComment.setDate(new Date());
        return commentRepository.save(newComment);
    }

    @Override
    public void delete(Long idArticle, Long id) {
        commentRepository.delete(getById(idArticle, id));
    }

    @Override
    public void deleteAllByIdArticle(Long idArticle) {
        commentRepository.deleteAllByArticle(idArticle);
    }
}
