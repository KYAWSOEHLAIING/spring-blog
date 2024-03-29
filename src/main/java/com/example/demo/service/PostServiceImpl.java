package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post create(Post post) {
        return postRepository.save(post) ;
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id +"Not Found"));
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Transactional
    @Override
    public void update(long id, Post postUpdate) {
        Post post = findById(id);
        post.setAuthor(postUpdate.getAuthor());
        post.setBody(postUpdate.getBody());
        post.setLastUpdated(postUpdate.getLastUpdated());
        post.setTag(postUpdate.getTag());
    }

    @Override
    public void delete(long id) {
        postRepository.deleteById(id);
    }
}
