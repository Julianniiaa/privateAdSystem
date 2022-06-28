package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.Category;
import com.example.privateadsystem.model.SubCategory;
import com.example.privateadsystem.service.CategoryService;
import com.example.privateadsystem.service.SubCategoryService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subCategories")
public class SubCategoryController {
    CategoryService categoryService;
    SubCategoryService subCategoryService;
    SubCategoryController(CategoryService categoryService,
                          SubCategoryService subCategoryService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    @GetMapping()
    public List<SubCategory> getAllCategories() {
        return subCategoryService.getAllSubCategories();
    }

    @GetMapping("/{id}")
    public List<SubCategory> getAllSubCategoriesByCategory(@PathVariable(name = "id") long id) {
        return subCategoryService.getSubCategoriesByCategory(id);
    }

    @PostMapping()
    public String createSubCategory(@RequestBody SubCategory subCategory) {
        subCategoryService.saveSubCategory(subCategory);
        return "redirect:/subCategories";
    }

    @DeleteMapping("/{id}")
    public String deleteSubCategory(@PathVariable(name = "id") long id) {
        subCategoryService.deleteSubCategory(id);
        return "redirect:/subCategories";
    }

    @PostMapping("/{id}")
    public String updateSubCategory(@PathVariable(name = "id") long id,
                                 @RequestBody SubCategory subCategory) {
        subCategoryService.updateSubCategory(id, subCategory);
        return "redirect:/subCategories";
    }
}
