package com.example.bookstore.service;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author author;
    private Page<Author> authorPage;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        author = new Author("Author Name");
        authorPage = new PageImpl<>(Arrays.asList(author));
    }

    @Test
    void testSearchAuthorByName(){
        when(authorRepository.findByNameContaining("Author Name",PageRequest.of(0, 10))).thenReturn(authorPage);
        Page<Author> result = authorService.searchAuthorsByName("Author Name",PageRequest.of(0,10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Author Name", result.getContent().get(0).getName());
    }

    @Test
    void testGetAllAuthors() {
        when(authorRepository.findAll(PageRequest.of(0, 10))).thenReturn(authorPage);

        Page<Author> result = authorService.getAllAuthors(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Author Name", result.getContent().get(0).getName());
    }

    @Test
    void testGetAuthorById() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author result = authorService.getAuthorById(1L);

        assertNotNull(result);
        assertEquals("Author Name", result.getName());
    }

    @Test
    void testAddAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author result = authorService.createAuthor(author);

        assertNotNull(result);
        assertEquals("Author Name", result.getName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(authorRepository).deleteById(1L);

        authorService.deleteAuthor(1L);

        verify(authorRepository, times(1)).deleteById(1L);
    }
}
