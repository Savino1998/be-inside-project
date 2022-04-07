package com.reply.insideproject.repository;

import com.reply.insideproject.model.Article;
import com.reply.insideproject.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    void deleteAllByArticle(Article article);

}
