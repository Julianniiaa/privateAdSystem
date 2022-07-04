package com.example.privateadsystem.repository;

import com.example.privateadsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Query("DELETE FROM Category c WHERE c.idCategory=?1")
    int delete(long id);
}
