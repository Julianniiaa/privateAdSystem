package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.model.Comment;
import com.example.privateadsystem.model.Post;
import com.example.privateadsystem.repository.CommentRepository;
import com.example.privateadsystem.repository.PostRepository;
import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.CommentService;
import com.example.privateadsystem.web.dto.CommentDto;

import java.time.LocalDateTime;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    public CommentServiceImpl (CommentRepository commentRepository,
                        UserRepository userRepository,
                        PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Comment saveComment(CommentDto commentDto) {
        Comment comment = Comment.builder().publicationTime(LocalDateTime.now())
                .text(commentDto.getText())
                .user(userRepository.findByIdUser(commentDto.getIdUser()))
                .post(postRepository.findByIdPost(commentDto.getIdPost()))
                .build();
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentByPost(long idPost) {
        return commentRepository.findCommentsByPost_IdPost(idPost);
    }

    @Override
    public List<Comment> getCommentByUser(long idUser) {
        return commentRepository.findCommentsByUser_IdUser(idUser);
    }

    @Override
    public Comment updateComment(long id, CommentDto commentDto) {
        Comment comment = commentRepository.findCommentByIdComment(id);
        comment.setText(commentDto.getText());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }
}
