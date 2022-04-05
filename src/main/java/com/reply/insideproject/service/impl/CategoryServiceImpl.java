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
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(id, Category.class);
        });
    }

}
