package com.reply.insideproject.service;

import com.reply.insideproject.exception.NotFoundException;
import com.reply.insideproject.model.Category;
import com.reply.insideproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Bean
    public void init() {
        if (categoryRepository.findAll().isEmpty()) {
            categoryRepository.save(new Category(1L, "Food"));
            categoryRepository.save(new Category(2L, "Sport"));
            categoryRepository.save(new Category(3L, "Informatic"));
            categoryRepository.save(new Category(4L, "Cinema"));
        }
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(id, Category.class);
        });
    }

}
