package com.Projectbackend.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.Projectbackend.model.Post;
import com.Projectbackend.service.PostService;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    // ✅ GET all posts
    @GetMapping
    public List<Post> getPosts() {
        return service.getAllPosts();
    }

    // ✅ POST create new post (UPDATED)
    @PostMapping
    public Post createPost(@RequestBody Post post) {

        // 🔥 If role not sent, default = citizen
        if (post.getRole() == null || post.getRole().isEmpty()) {
            post.setRole("citizen");
        }

        return service.savePost(post);
    }

    // ✅ PUT update post
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return service.updatePost(id, post);
    }

    // ✅ DELETE post
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        service.deletePost(id);
        return "Deleted successfully";
    }
}