package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.Category;
import com.example.privateadsystem.model.Post;
import com.example.privateadsystem.service.CategoryService;
import com.example.privateadsystem.service.PostService;
import com.example.privateadsystem.web.dto.CategoryDto;
import com.example.privateadsystem.web.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/posting")
public class PostController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostService postService;

    @GetMapping("/posting")
    public List<Category> loadCategories() {
        List<Category> categoryList = categoryService.findAllCategories();
//        model.addAttribute("categories", categoryList);
        return categoryList;
    }

    @PostMapping("/posting/newPost")
    public String submitPublication(@RequestBody PostDto postDto) {
//        postService.savePost(post);

        return postDto.toString();
//        return "redirect:/";
    }

//    @GetMapping()
//    public String publication(Model model) {
//        MainController.fill(model, makeService, bodyService, engineService, colorService, regionService);
//        return "offer";
//    }
}
