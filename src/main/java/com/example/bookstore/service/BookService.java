package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Page<Book> searchBooksByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContaining(title, pageable);
    }

    public Page<Book> searchBooksByAuthor(String authorName, Pageable pageable) {
        return bookRepository.findByAuthor_NameContaining(authorName, pageable);
    }

    public Page<Book> searchBooksByGenre(String genreName, Pageable pageable) {
        return bookRepository.findByGenre_NameContaining(genreName, pageable);
    }

    public Page<Book> searchBooksByPrice(double price, Pageable pageable) {
        return bookRepository.findByPrice(price, pageable);
    }

    public Page<Book> getAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public Book getBookById(long id){
        return bookRepository.findById(id);
    }

    public void deleteBook(long id){
        bookRepository.deleteById(id);
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getBookList(){
        return bookRepository.findAll();
    }
}