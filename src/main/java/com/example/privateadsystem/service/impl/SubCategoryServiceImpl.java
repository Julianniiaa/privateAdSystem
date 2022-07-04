package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.exception.NotEntityException;
import com.example.privateadsystem.model.Category;
import com.example.privateadsystem.model.SubCategory;
import com.example.privateadsystem.repository.CategoryRepository;
import com.example.privateadsystem.repository.SubCategoryRepository;
import com.example.privateadsystem.service.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    private final SubCategoryRepository subCategoryRepository;
    SubCategoryServiceImpl(CategoryRepository categoryRepository,
                           SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public SubCategory saveSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public List<SubCategory> getSubCategoriesByCategory(long id) {
        return subCategoryRepository.findByCategory_IdCategory(id);
    }

    @Override
    public SubCategory updateSubCategory(long id, SubCategory subCategory) {
        subCategory.setIdSubCategory(id);
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public void deleteSubCategory(long id) {
        try {
            subCategoryRepository.deleteById(id);
        }
        catch (NotEntityException e) {
            logger.info("Cannot delete this subCategory {}", id);
            throw new NotEntityException("Cannot delete this subCategory");
        }
    }
}
