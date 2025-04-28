package com.example.bookstore.service;

import com.example.bookstore.model.Genre;
import com.example.bookstore.repository.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public Page<Genre> getAllGenres(Pageable pageable){
        return genreRepository.findAll(pageable);
    }

    public Genre getGenreById(Long id){
        return genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre not found!"));
    }

    public Genre createGenre(Genre genre){
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id){
        genreRepository.deleteById(id);
    }

    public Page<Genre> searchGenresByName(String name, Pageable pageable) {
        return genreRepository.findByNameContaining(name, pageable);
    }

}
