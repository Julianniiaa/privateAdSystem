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
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.publicationDate desc," +
            "p.statusVip desc, p.user.avgRating desc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndPublicationDateDesc();
//    List<Post> findPostsByStatusSoldOrderByStatusVipDescUserAvgRatingDescPublicationDateDesc(boolean statusSold);
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.publicationDate asc," +
            "p.statusVip desc, p.user.avgRating desc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndPublicationDateAsc();
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.price asc," +
            "p.statusVip desc, p.user.avgRating desc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndPriceAsc();
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.price desc," +
            "p.statusVip desc, p.user.avgRating desc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndPriceDesc();
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.title asc," +
            "p.statusVip desc, p.user.avgRating desc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndTitleAsc();
    @Query("SELECT p FROM Post p where p.statusSold = false order by p.title desc," +
            "p.statusVip desc, p.user.avgRating desc")
    List<Post> findPostsByStatusSoldAndStatusVipDescAndTitleDesc();
    List<Post> findPostsByUser_IdUser(long id);
    @Query("SELECT p FROM Post p where p.subCategory.category.idCategory = (:id)" +
            "and p.statusSold = false order by p.statusVip desc, p.user.avgRating desc")
    List<Post> findPostsBySubCategory_Category_IdCategory(@Param("id") long id);
    @Query("SELECT p FROM Post p where p.subCategory.idSubCategory = (:id)" +
            "and p.statusSold = false order by p.statusVip desc, p.user.avgRating desc")
    List<Post> findPostsBySubCategory_IdSubCategoryAndStatusSoldFalse(long id);
    Post findByIdPost(long id);
    List<Post> findPostsByStatusSoldOrderByPriceDesc(boolean status);
}
