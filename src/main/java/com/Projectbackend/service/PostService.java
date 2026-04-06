package com.Projectbackend.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.Projectbackend.model.Post;
import com.Projectbackend.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public Post savePost(Post post) {
        return repository.save(post);
    }

    public Post updatePost(Long id, Post post) {
        Post existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(post.getTitle());
            existing.setDescription(post.getDescription());
            existing.setCitizenName(post.getCitizenName());
            return repository.save(existing);
        }
        return null;
    }

    public void deletePost(Long id) {
        repository.deleteById(id);
    }
}