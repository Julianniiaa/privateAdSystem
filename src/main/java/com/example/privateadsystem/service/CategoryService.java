package com.example.privateadsystem.service;

import com.example.privateadsystem.model.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> findAllCategories();
    Category updateCategory(long id, Category category);
    void deleteCategory(long id);
}
