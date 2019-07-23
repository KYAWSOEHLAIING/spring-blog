package com.example.demo.service;

import com.example.demo.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {
    Author create(Author author);
    Author findById(Long id);
    List<Author> findAll();
}
