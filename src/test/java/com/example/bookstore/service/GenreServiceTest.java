package com.example.bookstore.service;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Genre;
import com.example.bookstore.repository.GenreRepository;
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

public class GenreServiceTest {

    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    GenreService genreService;

    private Genre genre;
    private Page<Genre> genrePage;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        genre = new Genre("Genre Name");
        genrePage = new PageImpl<>(Arrays.asList(genre));
    }

    @Test
    void testSearchGenreByName(){
        when(genreRepository.findByNameContaining("Genre Name", PageRequest.of(0,10))).thenReturn(genrePage);
        Page<Genre> result = genreService.searchGenresByName("Genre Name",PageRequest.of(0,10));

        assertNotNull(result);
        assertEquals(1,result.getTotalElements());
        assertEquals("Genre Name",result.getContent().get(0).getName());
    }

    @Test
    void getAllGenres(){
        when(genreRepository.findAll(PageRequest.of(0,10))).thenReturn(genrePage);

        Page<Genre> result = genreService.getAllGenres(PageRequest.of(0,10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Genre Name", result.getContent().get(0).getName());
    }
    @Test
    void testGetGenreById() {
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        Genre result = genreService.getGenreById(1L);

        assertNotNull(result);
        assertEquals("Genre Name", result.getName());
    }

    @Test
    void testAddGenre() {
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre result = genreService.createGenre(genre);

        assertNotNull(result);
        assertEquals("Genre Name", result.getName());
        verify(genreRepository, times(1)).save(genre);
    }

    @Test
    void testDeleteGenre() {
        doNothing().when(genreRepository).deleteById(1L);

        genreService.deleteGenre(1L);

        verify(genreRepository, times(1)).deleteById(1L);
    }
}
