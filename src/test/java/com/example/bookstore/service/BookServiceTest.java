package com.example.bookstore.service;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Genre;
import com.example.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;
    private Page<Book> bookPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Author author = new Author("Author Name");
        Genre genre = new Genre("Genre Name");
        book = new Book("Book Title", author, genre, 19.99);
        bookPage = new PageImpl<>(Arrays.asList(book));
    }


    @Test
    void testSearchBooksByTitle() {
        when(bookRepository.findByTitleContaining("Book Title", PageRequest.of(0, 10))).thenReturn(bookPage);

        Page<Book> result = bookService.searchBooksByTitle("Book Title", PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Book Title", result.getContent().get(0).getTitle());
    }

    @Test
    void testSearchBooksByAuthor() {
        when(bookRepository.findByAuthor_NameContaining("Author Name", PageRequest.of(0, 10))).thenReturn(bookPage);

        Page<Book> result = bookService.searchBooksByAuthor("Author Name", PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Book Title", result.getContent().get(0).getTitle());
    }

    @Test
    void testSearchBooksByGenre() {
        when(bookRepository.findByGenre_NameContaining("Genre Name", PageRequest.of(0, 10))).thenReturn(bookPage);

        Page<Book> result = bookService.searchBooksByGenre("Genre Name", PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Book Title", result.getContent().get(0).getTitle());
    }

    @Test
    void testSearchBooksByPrice() {
        when(bookRepository.findByPrice(19.99, PageRequest.of(0, 10))).thenReturn(bookPage);

        Page<Book> result = bookService.searchBooksByPrice(19.99, PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Book Title", result.getContent().get(0).getTitle());
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll(PageRequest.of(0, 10))).thenReturn(bookPage);

        Page<Book> result = bookService.getAllBooks(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Book Title", result.getContent().get(0).getTitle());
    }

    @Test
    void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(book);

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Book Title", result.getTitle());
    }

    @Test
    void testAddBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.addBook(book);

        assertNotNull(result);
        assertEquals("Book Title", result.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
