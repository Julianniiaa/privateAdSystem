package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.exception.DataBaseException;
import com.example.privateadsystem.model.Category;
import com.example.privateadsystem.repository.CategoryRepository;
import com.example.privateadsystem.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        try {
            return categoryRepository.save(category);
        }
        catch (DataIntegrityViolationException error) {
            logger.info("This category {} is already exists", category);
            throw new DataBaseException("This category is already exists");
        }
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(long id, Category category) {
        Category categoryFound = categoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Category not found for id::" + id));
        try {
            return categoryRepository.save(categoryFound);
        }
        catch (DataIntegrityViolationException error) {
            logger.info("This category {} is already exists", category);
            throw new DataBaseException("This category is already exists");
        }
    }

    @Override
    public void deleteCategory(long id) {
        try {
            categoryRepository.deleteById(id);
        }
        catch (Exception error) {
            logger.info("Cannot delete this category {}", id);
            throw new DataBaseException("Cannot delete this Category");
        }
    }
}
