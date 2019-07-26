package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.AuthorService;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/post")
    public String create(Model model){
        model.addAttribute("post",new Post());
        model.addAttribute("authors",authorService.findAll());
        return "postForm";
    }
    @PostMapping("/post")
    public String process(@Valid Post post, BindingResult result){
        if(result.hasErrors()){
            return "postForm";
        }
        postService.create(post);
        return "redirect:/posrs";
    }
    @GetMapping("/posts")
    public String showAllPosts(Model model){
        model.addAttribute("posts",postService.findAll());
        return "posts";
    }
 }