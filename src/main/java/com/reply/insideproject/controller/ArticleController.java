package com.reply.insideproject.controller;

import com.reply.insideproject.dto.ArticleDto;
import com.reply.insideproject.model.Article;
import com.reply.insideproject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<URI> create(@RequestBody ArticleDto article) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath().path("/articles/{id}")
                .buildAndExpand(articleService.create(article))
                .toUri()).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Article>> getByPage(@Param("offset") Integer offset, @Param("size") Integer size) {
        return ResponseEntity.ok(articleService.getByPage(offset, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleDto article) {
        return ResponseEntity.ok(articleService.update(id, article));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
