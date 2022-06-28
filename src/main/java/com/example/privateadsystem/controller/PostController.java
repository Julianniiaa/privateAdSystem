package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.Category;
import com.example.privateadsystem.model.Post;
import com.example.privateadsystem.model.SubCategory;
import com.example.privateadsystem.service.CategoryService;
import com.example.privateadsystem.service.PostService;
import com.example.privateadsystem.service.impl.CategoryServiceImpl;
import com.example.privateadsystem.service.impl.PostServiceImpl;
import com.example.privateadsystem.web.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final CategoryService categoryService;
    private final PostService postService;
    public PostController(CategoryService categoryService,
                          PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @PostMapping()
    public Post createPublication(@RequestBody PostDto postDto) {
        return postService.savePost(postDto);
    }

    @GetMapping("/datetime")
    public List<Post> getAllUnsoldPostsDatetimeDesc() {
        return postService.getAllUnsoldPostsDatetimeDesc();
    }

    @GetMapping("categories/{id}")
    public List<Post> getAllUnsoldPostsByCategory(@PathVariable(name = "id") long id) {
        return postService.getAllUnsoldPostsByCategory(id);
    }

    @GetMapping("/subCategories/{id}")
    public List<Post> getAllUnsoldPostsBySubCategories(@PathVariable(name = "id") long id) {
        return postService.getAllUnsoldPostsBySubCategory(id);
    }

    @PostMapping("/{id}")
    public Post updatePost(@PathVariable(name = "id") long id,
                                 @RequestBody PostDto postDto) {

        return postService.updatePost(id, postDto);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
