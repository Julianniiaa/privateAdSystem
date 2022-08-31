package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.*;
import com.example.privateadsystem.service.*;
import com.example.privateadsystem.model.dto.CommentDto;
import com.example.privateadsystem.model.dto.FavoriteDto;
import com.example.privateadsystem.model.dto.PostDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final PostService postService;
    private final FavoriteService favoriteService;
    private final CommentService commentService;
    private final RegionService regionService;
    public PostController(CategoryService categoryService,
                          SubCategoryService subCategoryService,
                          PostService postService,
                          FavoriteService favoriteService,
                          CommentService commentService,
                          RegionService regionService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.postService = postService;
        this.favoriteService = favoriteService;
        this.commentService = commentService;
        this.regionService = regionService;
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        logger.info("Get all categories and regions");
        List<Category> categories = categoryService.findAllCategories();
        List<Region> regions = regionService.getAllRegions();
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable(name = "id") long id) {
        logger.info("Get post {}", id);
        return postService.getPost(id);
    }

    @PostMapping()
    public Post createPost(@RequestBody PostDto postDto) {
        logger.info("Create post {}", postDto);
        return postService.savePost(postDto);
    }

    @PostMapping("/{id}")
    public Post updatePost(@PathVariable(name = "id") long id,
                                 @RequestBody PostDto postDto) {
        logger.info("Update post {}, {}", id, postDto);
        return postService.updatePost(id, postDto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable(name = "id") long id) {
        logger.info("Delete post {}", id);
        postService.deletePost(id);
    }

    @PostMapping("/{id}/favorites")
    public Favorite createFavorite(@PathVariable(name = "id") long id,
                                    @RequestParam(name = "idUser") long idUser) {
        logger.info("Like post {} by {}", id, idUser);
        FavoriteDto favoriteDto = FavoriteDto.builder()
                .idPost(id)
                .idUser(idUser)
                .build();
        return favoriteService.saveFavorite(favoriteDto);
    }

    @PostMapping("/{id}/comments")
    public Comment createComment(@PathVariable(name = "id") long id,
                                 @RequestBody CommentDto commentDto) {
        logger.info("Create comment under {} {}", id, commentDto);
        commentDto.setIdPost(id);
        return commentService.saveComment(commentDto);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getAllCommentsByPost(@PathVariable(name = "id") long id) {
        logger.info("Get all comments by post {}", id);
        return commentService.getCommentByPost(id);
    }
}
