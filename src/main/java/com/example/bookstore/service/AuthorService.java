package com.example.bookstore.service;

import com.example.bookstore.model.Author;
import com.example.bookstore.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Page<Author> getAllAuthors(Pageable pageable){
        return authorRepository.findAll(pageable);
    }

    public Author getAuthorById(Long id){
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found!"));
    }

    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id){
        authorRepository.deleteById(id);
    }
    public Page<Author> searchAuthorsByName(String name, Pageable pageable) {
        return authorRepository.findByNameContaining(name, pageable);
    }
}
