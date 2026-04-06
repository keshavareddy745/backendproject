package com.Projectbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Projectbackend.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}