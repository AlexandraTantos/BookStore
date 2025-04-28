package com.example.bookstore.repository;

import com.example.bookstore.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
    public Page<Genre> findByNameContaining(String name, Pageable page);
}
