package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.Category;
import com.example.privateadsystem.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    CategoryService categoryService;
    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        logger.info("Get all categories");
        return categoryService.findAllCategories();
    }

    @PostMapping()
    public Category createCategory(@RequestBody Category category) {
        logger.info("Create category {}", category);
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable(name = "id") long id) {
        logger.info("Delete category {}", id);
        categoryService.deleteCategory(id);
    }

    @PostMapping("/{id}")
    public Category updateCategory(@PathVariable(name = "id") long id,
                                 @RequestBody Category category) {
        logger.info("Update category {}, {}", id, category);
        return categoryService.updateCategory(id, category);
    }
}
