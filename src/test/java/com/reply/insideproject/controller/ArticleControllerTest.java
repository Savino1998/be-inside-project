package com.reply.insideproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reply.insideproject.advice.RestExceptionHandler;
import com.reply.insideproject.dto.ArticleDto;
import com.reply.insideproject.model.Article;
import com.reply.insideproject.model.Category;
import com.reply.insideproject.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ArticleControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

    private Article article;

    private ArticleDto articleDto;

    @BeforeEach
    private void init() {

        mockMvc = MockMvcBuilders.standaloneSetup(articleController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();

        Category category = new Category(1L, "Food");

        articleDto = new ArticleDto(category.getId(), "La pasta è buona", "Pasta");

        article = new Article(1L, category, articleDto.getName(), articleDto.getBody());

    }

    @Test
    void create() throws Exception {

        lenient().when(articleService.create(any(ArticleDto.class))).thenReturn(article);

        mockMvc.perform(MockMvcRequestBuilders.post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(articleDto)))
                .andExpect(status().isCreated());

        verify(articleService).create(any(ArticleDto.class));

    }

    @Test
    void getById() throws Exception {

        lenient().when(articleService.getById(any(Long.class))).thenReturn(any(Article.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/articles/{id}", 1L)).andExpect(status().isOk());

        verify(articleService).getById(any(Long.class));

    }

    @Test
    void getByPage() throws Exception {

        lenient().when(articleService.getByPage(any(Integer.class), any(Integer.class)))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        mockMvc.perform(MockMvcRequestBuilders.get("/articles")
                        .param("offset", String.valueOf(0))
                        .param("size", String.valueOf(20)))
                .andExpect(status().isOk());

        verify(articleService).getByPage(any(Integer.class), any(Integer.class));

    }

    @Test
    void update() throws Exception {

        lenient().when(articleService.update(any(Long.class), any(ArticleDto.class))).thenReturn(article);

        mockMvc.perform(MockMvcRequestBuilders.put("/articles/{id}", article.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(articleDto)))
                .andExpect(status().isOk());

        verify(articleService).update(any(Long.class), any(ArticleDto.class));

    }

    @Test
    void delete() throws Exception {

        lenient().doNothing().when(articleService).delete(any(Long.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/articles/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(articleService).delete(any(Long.class));

    }

}