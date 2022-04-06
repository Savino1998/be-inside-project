package com.reply.insideproject.controller;

import com.reply.insideproject.dto.CommentDto;
import com.reply.insideproject.model.Comment;
import com.reply.insideproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/articles/{idArticle}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<URI> create(@PathVariable Long idArticle, @RequestBody CommentDto comment) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath().path("/articles/{id}")
                .buildAndExpand(commentService.create(idArticle, comment).getId())
                .toUri()).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getById(@PathVariable Long idArticle, @PathVariable Long id) {
        return ResponseEntity.ok(commentService.getById(idArticle, id));
    }

    @GetMapping
    public ResponseEntity<Page<Comment>> getByPage(@PathVariable Long idArticle, @Param("offset") Integer offset, @Param("size") Integer size) {
        return ResponseEntity.ok(commentService.getByPage(idArticle, offset, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long idArticle, @PathVariable Long id, @RequestBody CommentDto comment) {
        return ResponseEntity.ok(commentService.update(idArticle, id, comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long idArticle, @PathVariable Long id) {
        commentService.delete(idArticle, id);
        return ResponseEntity.noContent().build();
    }

}
