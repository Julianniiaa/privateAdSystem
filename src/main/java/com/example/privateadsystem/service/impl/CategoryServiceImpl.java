package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.model.Category;
import com.example.privateadsystem.repository.CategoryRepository;
import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.CategoryService;
import com.example.privateadsystem.web.dto.CategoryDto;
import org.hibernate.HibernateError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super();
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        try {
            categoryRepository.save(category);
        }
        catch (Exception error) {
            System.out.println("This category is already exists");
            return null;
        }
        return category;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(long id, Category category) {
        category.setIdCategory(id);
        try {
            categoryRepository.save(category);
        }
        catch (Exception error) {
            System.out.println("This category is already exists");
            return null;
        }
        return category;
    }

    @Override
    public void deleteCategory(long id) {
        try {
            categoryRepository.deleteById(id);
        }
        catch (Exception e) {
            System.out.println("Cannot delete this Category");
        }
    }
}
