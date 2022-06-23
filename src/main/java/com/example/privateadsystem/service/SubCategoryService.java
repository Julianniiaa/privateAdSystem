package com.example.privateadsystem.service;

import com.example.privateadsystem.model.Category;
import com.example.privateadsystem.model.SubCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubCategoryService {
    SubCategory saveSubCategory(SubCategory subCategory);
    List<SubCategory> getAllSubCategories();
    List<SubCategory> getSubCategoriesByCategory(long id);
    SubCategory updateSubCategory(long id, SubCategory subCategory);
    void deleteSubCategory(long id);
}
