package com.example.privateadsystem.repository;

import com.example.privateadsystem.model.Post;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.statusVip desc," +
            "p.publicationDate desc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndPublicationDateDesc();
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.statusVip desc," +
            "p.publicationDate asc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndPublicationDateAsc();
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.statusVip desc," +
            "p.price asc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndPriceAsc();
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.statusVip desc," +
            "p.price desc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndPriceDesc();
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.statusVip desc," +
            "p.title asc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndTitleAsc();
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.statusVip desc," +
            "p.title desc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndTitleDesc();
    List<Post> findPostsByUser_IdUser(long id);
    @Query("SELECT p FROM Post p where p.subCategory.category.idCategory = (:id)" +
            "and p.statusSold = false order by p.statusVip desc")
    List<Post> findPostsBySubCategory_Category_IdCategory(@Param("id") long id);
    List<Post> findPostsBySubCategory_IdSubCategoryAndStatusSoldFalse(long id);
    Post findById(long id);
}
