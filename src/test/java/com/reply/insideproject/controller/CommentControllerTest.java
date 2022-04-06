package com.reply.insideproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reply.insideproject.advice.RestExceptionHandler;
import com.reply.insideproject.dto.CommentDto;
import com.reply.insideproject.model.Article;
import com.reply.insideproject.model.Comment;
import com.reply.insideproject.service.CommentService;
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
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private Comment comment;

    private CommentDto commentDto;

    private String basicUrl = "/articles/{idArticle}/comments";

    @BeforeEach
    private void init() {

        mockMvc = MockMvcBuilders.standaloneSetup(commentController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();

        commentDto = new CommentDto("jack", "Preferisco la carne");

        comment = new Comment(1L, new Article(), commentDto.getNickname(), commentDto.getBody(), new Date());

    }

    @Test
    void create() throws Exception {

        lenient().when(commentService.create(any(Long.class), any(CommentDto.class))).thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders.post(basicUrl, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(commentDto)))
                .andExpect(status().isCreated());

        verify(commentService).create(any(Long.class), any(CommentDto.class));

    }

    @Test
    void getById() throws Exception {

        lenient().when(commentService.getById(any(Long.class), any(Long.class))).thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders.get(basicUrl + "/{id}", 1L, comment.getId()))
                .andExpect(status().isOk());

        verify(commentService).getById(any(Long.class), any(Long.class));

    }

    @Test
    void getByPage() throws Exception {

        lenient().when(commentService.getByPage(any(Long.class), any(Integer.class), any(Integer.class)))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        mockMvc.perform(MockMvcRequestBuilders.get(basicUrl, comment.getId())
                        .param("offset", String.valueOf(0))
                        .param("size", String.valueOf(20)))
                .andExpect(status().isOk());

        verify(commentService).getByPage(any(Long.class), any(Integer.class), any(Integer.class));

    }

    @Test
    void update() throws Exception {

        lenient().when(commentService.update(any(Long.class), any(Long.class), any(CommentDto.class)))
                .thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders.put(basicUrl + "/{id}", 1L, comment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(commentDto)))
                .andExpect(status().isOk());

        verify(commentService).update(any(Long.class), any(Long.class), any(CommentDto.class));

    }

    @Test
    void delete() throws Exception {

        lenient().doNothing().when(commentService).delete(any(Long.class), any(Long.class));

        mockMvc.perform(MockMvcRequestBuilders.delete(basicUrl + "/{id}", 1L, comment.getId()))
                .andExpect(status().isNoContent());

        verify(commentService).delete(any(Long.class), any(Long.class));

    }

}