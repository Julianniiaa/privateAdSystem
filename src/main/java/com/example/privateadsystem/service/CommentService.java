package com.example.privateadsystem.service;

import com.example.privateadsystem.model.Comment;
import com.example.privateadsystem.model.dto.CommentDto;

import java.util.List;

public interface CommentService {
    Comment saveComment(CommentDto commentDto);
    List<Comment> getCommentByPost(long idPost);
    List<Comment> getCommentByUser(long idUser);
    Comment updateComment(long id, CommentDto commentDto);
    void deleteComment(long id);
}
