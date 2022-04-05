package com.reply.insideproject.service.impl;

import com.reply.insideproject.dto.ArticleDto;
import com.reply.insideproject.exception.AlreadyExistsException;
import com.reply.insideproject.exception.NotFoundException;
import com.reply.insideproject.model.Article;
import com.reply.insideproject.repository.ArticleRepository;
import com.reply.insideproject.repository.CommentRepository;
import com.reply.insideproject.service.ArticleService;
import com.reply.insideproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Article create(ArticleDto article) {
        if (articleRepository.findByName(article.getName()).isPresent()) {
            throw new AlreadyExistsException(article.getName(), Article.class);
        }
        return articleRepository.save(new Article(categoryService.getById(article.getIdCategory()), article.getName(), article.getBody()));
    }

    @Override
    public Article getById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(id, Article.class);
        });
    }

    @Override
    public Page<Article> getByPage(Integer offset, Integer size) {
        return articleRepository.findAll(PageRequest.of(offset, size));
    }

    @Override
    public Article update(Long id, ArticleDto article) {
        Article oldArticle = getById(id);
        if (article.getName() != null) {
            oldArticle.setName(article.getName());
        }
        if (article.getBody() != null) {
            oldArticle.setBody(article.getBody());
        }
        if (article.getIdCategory() != null) {
            oldArticle.setCategory(categoryService.getById(article.getIdCategory()));
        }
        return articleRepository.save(oldArticle);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        articleRepository.delete(getById(id));
        commentRepository.deleteAllByArticle(id);
    }

    @Override
    public void checkArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new NotFoundException(id, Article.class);
        }
    }

}
