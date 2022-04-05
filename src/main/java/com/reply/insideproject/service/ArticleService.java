package com.reply.insideproject.service;

import com.reply.insideproject.dto.ArticleDto;
import com.reply.insideproject.model.Article;
import org.springframework.data.domain.Page;

public interface ArticleService {

    Article create(ArticleDto article);

    Article getById(Long id);

    Page<Article> getByPage(Integer offset, Integer size);

    Article update(Long id, ArticleDto article);

    void delete(Long id);

}
