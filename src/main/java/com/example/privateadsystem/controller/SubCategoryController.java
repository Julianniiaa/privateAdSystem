package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.SubCategory;
import com.example.privateadsystem.service.CategoryService;
import com.example.privateadsystem.service.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subCategories")
public class SubCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);

    CategoryService categoryService;
    SubCategoryService subCategoryService;
    SubCategoryController(CategoryService categoryService,
                          SubCategoryService subCategoryService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    @GetMapping()
    public List<SubCategory> getAllSubCategories() {
        logger.info("Get all subCategories");
        return subCategoryService.getAllSubCategories();
    }

    @PostMapping()
    public SubCategory createSubCategory(@RequestBody SubCategory subCategory) {
        logger.info("Create subCategory {}", subCategory);
        return subCategoryService.saveSubCategory(subCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteSubCategory(@PathVariable(name = "id") long id) {
        logger.info("Delete SubCategory {}", id);
        subCategoryService.deleteSubCategory(id);
    }

    @PostMapping("/{id}")
    public SubCategory updateSubCategory(@PathVariable(name = "id") long id,
                                 @RequestBody SubCategory subCategory) {
        logger.info("Update subCategory {}, {}", id, subCategory);
        return subCategoryService.updateSubCategory(id, subCategory);
    }
}
