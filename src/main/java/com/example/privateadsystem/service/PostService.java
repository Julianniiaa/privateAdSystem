package com.example.privateadsystem.service;

import com.example.privateadsystem.model.Post;
import com.example.privateadsystem.repository.PostRepository;
import com.example.privateadsystem.web.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void savePost(Post post) {
        postRepository.save(post);
    }
}
