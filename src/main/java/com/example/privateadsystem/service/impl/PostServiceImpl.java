package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.model.Post;
import com.example.privateadsystem.repository.PostRepository;
import com.example.privateadsystem.repository.RegionRepository;
import com.example.privateadsystem.repository.SubCategoryRepository;
import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.PostService;
import com.example.privateadsystem.web.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;
    private final SubCategoryRepository subCategoryRepository;
    public PostServiceImpl(PostRepository postRepository,
                           RegionRepository regionRepository,
                           UserRepository userRepository,
                           SubCategoryRepository subCategoryRepository) {
        super();
        this.postRepository = postRepository;
        this.regionRepository = regionRepository;
        this.userRepository = userRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public Post savePost(PostDto postDto) {
        Post post = Post.builder().user(userRepository.findByIdUser(postDto.getIdUser()))
                .subCategory(subCategoryRepository.findByIdSubCategory(postDto.getIdSubCategory()))
                .region(regionRepository.findByIdRegion(postDto.getIdRegion()))
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .price((postDto.getPrice()))
                .publicationDate(LocalDateTime.now())
                .statusSold(false)
                .statusVip(postDto.isStatusVip())
                .build();
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllUnsoldPostsDatetimeDesc() {
        return postRepository.findPostsByStatusSoldAndStatusVipDescAndPublicationDateDesc();
    }

    @Override
    public List<Post> getAllUnsoldPostsDatetimeAsc() {
        return postRepository.findPostsByStatusSoldAndStatusVipDescAndPublicationDateAsc();
    }

    @Override
    public List<Post> getAllUnsoldPostsPriceAsc() {
        return postRepository.findPostsByStatusSoldAndStatusVipDescAndPriceAsc();
    }

    @Override
    public List<Post> getAllUnsoldPostsPriceDesc() {
        return postRepository.findPostsByStatusSoldAndStatusVipDescAndPriceDesc();
    }

    @Override
    public List<Post> getAllUnsoldPostsTitleAsc() {
        return postRepository.findPostsByStatusSoldAndStatusVipDescAndTitleAsc();
    }

    @Override
    public List<Post> getAllUnsoldPostsTitleDesc() {
        return postRepository.findPostsByStatusSoldAndStatusVipDescAndTitleDesc();
    }

    @Override
    public List<Post> getAllPostsByUser(long id) {
        return postRepository.findPostsByUser_IdUser(id);
    }

    @Override
    public List<Post> getAllUnsoldPostsByCategory(long id) {
        return postRepository.findPostsBySubCategory_Category_IdCategory(id);
    }

    @Override
    public List<Post> getAllUnsoldPostsBySubCategory(long id) {
        return postRepository.findPostsBySubCategory_IdSubCategoryAndStatusSoldFalse(id);
    }

    @Override
    public Post getPost(long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post updatePost(long id, PostDto postDto) {
        Post oldPost = postRepository.findById(id);
        oldPost.setTitle(postDto.getTitle());
        oldPost.setDescription(postDto.getDescription());
        oldPost.setPrice(postDto.getPrice());
        oldPost.setStatusSold(postDto.isStatusSold());
        oldPost.setSubCategory(subCategoryRepository.findByIdSubCategory(postDto.getIdSubCategory()));
        oldPost.setRegion(regionRepository.findByIdRegion(postDto.getIdRegion()));
        return postRepository.save(oldPost);
    }

    @Override
    public void deletePost(long id) {
        try {
            postRepository.deleteById(id);
        }
        catch (Exception e) {
            System.out.println("Cannot delete post");
            e.toString();
        }
    }
}
