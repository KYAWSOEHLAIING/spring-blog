package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.AuthorService;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;
    private long updateId;

    @GetMapping("/post")
    public String create(Model model){
        model.addAttribute("post",new Post());
        model.addAttribute("authors",authorService.findAll());
        return "postForm";
    }
    @PostMapping("/post")
    public String process(@Valid Post post, BindingResult result,RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "postForm";
        }
        postService.create(post);
        redirectAttributes.addFlashAttribute("insert",true);
        return "redirect:/posts";
    }
    @GetMapping("/posts")
    public String showAllPosts(Model model){
        model.addAttribute("posts",postService.findAll());
        model.addAttribute("success",model.containsAttribute("success"));
        model.addAttribute("insert",model.containsAttribute("insert"));
        return "posts";
    }
    @GetMapping("/posts/details/{id}")
    public String showDetails(Model model, @PathVariable("id") long id){
        model.addAttribute("post",postService.findById(id));
        return "postDetails";
    }
    @GetMapping("/posts/update/{id}")
    public String updatePost(Model model,@PathVariable("id") long id){

        this.updateId = id;
        model.addAttribute("post",postService.findById(id));
        model.addAttribute("authors",authorService.findAll());
        return "postUpdateForm";

    }

    @PostMapping("/posts/update")
    public String processPost(Post post, RedirectAttributes redirectAttributes){
        postService.update(updateId,post);
        redirectAttributes.addFlashAttribute("success",true);
        return "redirect:/posts";
    }
    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable("id") long id){
        postService.delete(id);
        return "redirect:/posts";
    }
    @GetMapping(value="posts/pdf",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdfReport(){
        ByteArrayInputStream bis=PdfReportForPost.postPdfView(postService.findAll());
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition","inline;filename=postsreport.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

 }
