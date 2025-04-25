package com.example.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitleContaining(String title, Pageable pageable);

    Page<Book> findByAuthor_NameContaining(String authorName, Pageable pageable);

    Page<Book> findByGenre_NameContaining(String genreName, Pageable pageable);

    Page<Book> findByPrice(double price, Pageable pageable);

    Book findById(long id);

}
