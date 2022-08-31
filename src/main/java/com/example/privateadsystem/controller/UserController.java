package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.Comment;
import com.example.privateadsystem.model.Favorite;
import com.example.privateadsystem.model.Post;
import com.example.privateadsystem.model.User;
import com.example.privateadsystem.service.CommentService;
import com.example.privateadsystem.service.FavoriteService;
import com.example.privateadsystem.service.PostService;
import com.example.privateadsystem.service.UserService;
import com.example.privateadsystem.model.dto.CommentDto;
import com.example.privateadsystem.model.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final PostService postService;
    private final FavoriteService favoriteService;
    private final CommentService commentService;

    public UserController(UserService userService,
                          PostService postService,
                          FavoriteService favoriteService,
                          CommentService commentService) {
        this.userService = userService;
        this.postService = postService;
        this.favoriteService = favoriteService;
        this.commentService = commentService;
    }

    @PostMapping()
    public User createUser(@RequestBody UserDto userDto) throws ParseException {
        logger.info("Create user {}", userDto);
        return userService.saveUser(userDto);
    }

    @GetMapping
    public List<User> getAllUsers() {
        logger.info("Get all users");
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(name = "id") long id) {
        logger.info("Get user {}", id);
        return userService.getUserById(id);
    }

    @PostMapping("/{id}")
    public User updateUser(@PathVariable (name = "id") long id,
                           @RequestBody UserDto userDto) throws ParseException {
        logger.info("Update user {}, {}", id, userDto);
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") long id) {
        logger.info("Delete user {}", id);
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/myPosts")
    public List<Post> getAllPostsByUser(@PathVariable(name = "id") long id) {
        logger.info("Get all posts by user {}", id);
        return postService.getAllPostsByUser(id);
    }

    @GetMapping("/{id}/favorites")
    public List<Favorite> getAllFavoritesByUser(@PathVariable(name = "id") long id) {
        logger.info("Get all favorites by user {}", id);
        return favoriteService.getAllByUser(id);
    }

    @DeleteMapping("/favorites/{id}")
    public void deleteFavorite(@PathVariable(name = "id") long id) {
        logger.info("Delete like by {}", id);
        favoriteService.deleteFavorite(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getAllCommentsByUser(@PathVariable(name = "id") long id) {
        logger.info("Get all comments by user {}", id);
        return commentService.getCommentByUser(id);
    }

    @PostMapping("/{id}/comments/{idComment}")
    public Comment updateComment(@PathVariable(name = "id") long id,
                                 @PathVariable(name = "idComment") long idComment,
                                 @RequestBody CommentDto commentDto) {
        logger.info("Update comment by {} {}, {}", id, idComment, commentDto);
        commentDto.setIdUser(id);
        return commentService.updateComment(idComment, commentDto);
    }

    @DeleteMapping("/{id}/comments/{idComment}")
    public void deleteComment(@PathVariable(name = "id") long id,
                              @PathVariable(name = "idComment") long idComment) {
        logger.info("Delete comment by {} {}", id, idComment);
        commentService.deleteComment(idComment);
    }
}
