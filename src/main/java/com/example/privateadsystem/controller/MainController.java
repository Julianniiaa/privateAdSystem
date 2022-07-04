package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.Post;
import com.example.privateadsystem.model.SubCategory;
import com.example.privateadsystem.service.CategoryService;
import com.example.privateadsystem.service.PostService;
import com.example.privateadsystem.service.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final PostService postService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    public MainController(PostService postService,
                          CategoryService categoryService,
                          SubCategoryService subCategoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        logger.info("Get all posts by datetime desc");
        return postService.getAllUnsoldPostsDatetimeDesc();
    }

    @GetMapping("/sorts")
    public List<Post> getSortedPosts(@RequestParam(value = "sort") String sort) {
        List<Post> posts = null;
        logger.info("Sort posts by {}", sort);
        switch (sort) {
            case "byDateDesc" -> posts =  postService.getAllUnsoldPostsDatetimeDesc();
            case "byDateAsc" -> posts = postService.getAllUnsoldPostsDatetimeAsc();
            case "byPriceDesc" -> posts = postService.getAllUnsoldPostsPriceDesc();
            case "byPriceAsc" -> posts = postService.getAllUnsoldPostsPriceAsc();
            case "byTitleAsc" -> posts = postService.getAllUnsoldPostsTitleAsc();
            case "byTitleDesc" -> posts = postService.getAllUnsoldPostsTitleDesc();
        }
        return posts;
    }

    @GetMapping("/searches")
    public List<Post> getSearchedPosts(
            @RequestParam(value = "category", required = false, defaultValue = "0") String category,
            @RequestParam(value = "subCategory", required = false, defaultValue = "0") String subCategory) {
        List<Post> posts = null;
        logger.info("Search post by parameters");
        if (Objects.equals(subCategory, "0"))
            posts = postService.getAllUnsoldPostsByCategory(Long.parseLong(category));
        else posts = postService.getAllUnsoldPostsBySubCategory(Long.parseLong(subCategory));
        return posts;
    }

    @GetMapping(value=  {"/subCategories/{id}",
                         "/searches/subcategories/{id}",
                        "/posts/subCategories{id}"})
    public List<SubCategory> getAllSubCategoriesByCategory(@PathVariable("id") long id) {
        logger.info("Get categories");
        return subCategoryService.getSubCategoriesByCategory(id);
    }
}
