package com.reply.insideproject.service;

import com.reply.insideproject.model.Category;
import com.reply.insideproject.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void getCategories() {

        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> categoryService.getCategories());
        verify(categoryRepository).findAll();

    }

    @Test
    void getById() {

        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(new Category(1L, "Food")));
        assertDoesNotThrow(() -> categoryService.getById(1L));
        verify(categoryRepository).findById(any(Long.class));

    }

}