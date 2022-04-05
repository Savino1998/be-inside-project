package com.reply.insideproject.service.impl;

import com.reply.insideproject.exception.NotFoundException;
import com.reply.insideproject.model.Category;
import com.reply.insideproject.repository.CategoryRepository;
import com.reply.insideproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        List<Category> list = categoryRepository.findAll();
        if (list.isEmpty()) {
            categoryRepository.save(new Category(1L, "Food"));
            categoryRepository.save(new Category(2L, "Sport"));
            categoryRepository.save(new Category(3L, "Informatic"));
            categoryRepository.save(new Category(4L, "Cinema"));
            list = categoryRepository.findAll();
        }
        return list;
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(id, Category.class);
        });
    }

}
