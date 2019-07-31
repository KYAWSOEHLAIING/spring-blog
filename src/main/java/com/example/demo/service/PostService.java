package com.example.demo.service;

import com.example.demo.model.Post;

import java.util.List;

public interface PostService {
    Post create(Post post);
    Post findById(Long id);
    List<Post> findAll();
    void update(long id,Post postUpdate);
}
