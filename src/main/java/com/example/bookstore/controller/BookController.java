package com.example.bookstore.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook,HttpStatus.CREATED);
    }

    @GetMapping("/search/title")
    public ResponseEntity<Page<Book>> searchBooksByTitle(@RequestParam String title,Pageable pageable){
        Page<Book> books = bookService.searchBooksByTitle(title, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    } 

    @GetMapping("/search/author")
    public ResponseEntity<Page<Book>> searchBooksByAuthor(@RequestParam String author,Pageable pageable){
        Page<Book> books = bookService.searchBooksByAuthor(author, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    } 

    @GetMapping("/search/genre")
    public ResponseEntity<Page<Book>> searchBooksByGenre(@RequestParam String genre,Pageable pageable){
        Page<Book> books = bookService.searchBooksByGenre(genre, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    } 

    @GetMapping("/search/price")
    public ResponseEntity<Page<Book>> searchBooksByPrice(@RequestParam double price,Pageable pageable){
        Page<Book> books = bookService.searchBooksByPrice(price, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    } 

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable){
        Page<Book> books = bookService.getAllBooks(pageable);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
