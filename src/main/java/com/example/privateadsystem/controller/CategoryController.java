package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.Category;
import com.example.privateadsystem.service.CategoryService;
import com.example.privateadsystem.web.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    CategoryService categoryService;
    CategoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @PostMapping()
    public String createCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable(name = "id") long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @PostMapping("/{id}")
    public String updateCategory(@PathVariable(name = "id") long id,
                                 @RequestBody Category category) {
        categoryService.updateCategory(id, category);
        return "redirect:/categories";
    }
}
