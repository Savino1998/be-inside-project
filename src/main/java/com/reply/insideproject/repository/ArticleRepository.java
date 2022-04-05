package com.reply.insideproject.repository;

import com.reply.insideproject.model.Article;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

    Optional<Article> findByName(String name);

}
