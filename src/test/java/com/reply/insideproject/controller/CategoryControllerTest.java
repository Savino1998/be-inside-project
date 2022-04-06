package com.reply.insideproject.controller;

import com.reply.insideproject.advice.RestExceptionHandler;
import com.reply.insideproject.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    private void init() {

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();

    }

    @Test
    void getCategories() throws Exception {

        lenient().when(categoryService.getCategories()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/categories")).andExpect(status().isOk());

        verify(categoryService).getCategories();

    }

}