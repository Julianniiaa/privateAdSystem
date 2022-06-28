package com.example.privateadsystem.service;

import com.example.privateadsystem.model.Post;
import com.example.privateadsystem.web.dto.PostDto;

import java.util.List;

public interface PostService {
    Post savePost(PostDto postDto);
    List<Post> getAllUnsoldPostsDatetimeDesc();
    List<Post> getAllUnsoldPostsDatetimeAsc();
    List<Post> getAllUnsoldPostsPriceAsc();
    List<Post> getAllUnsoldPostsPriceDesc();
    List<Post> getAllUnsoldPostsTitleAsc();
    List<Post> getAllUnsoldPostsTitleDesc();
    List<Post> getAllPostsByUser(long id);
    List<Post> getAllUnsoldPostsByCategory(long id);
    List<Post> getAllUnsoldPostsBySubCategory(long id);
    Post getPost(long id);
    Post updatePost(long id, PostDto postDto);
    void deletePost(long id);
}
