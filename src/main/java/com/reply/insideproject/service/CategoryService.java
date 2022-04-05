package com.reply.insideproject.service;

import com.reply.insideproject.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    Category getById(Long id);

}
