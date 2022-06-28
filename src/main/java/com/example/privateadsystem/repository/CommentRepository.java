package com.example.privateadsystem.repository;

import com.example.privateadsystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByUser_IdUser(long id);
    List<Comment> findCommentsByPost_IdPost(long id);
    Comment findCommentByIdComment(long id);
}
