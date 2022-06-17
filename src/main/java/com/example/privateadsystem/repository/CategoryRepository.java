package com.example.privateadsystem.repository;

import com.example.privateadsystem.model.Category;
import com.example.privateadsystem.web.dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
