package com.example.bookstore.views;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Genre;
import com.example.bookstore.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookViewController {

    private final BookService bookService;

    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getBookList();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String author,
                              @RequestParam(required = false) String genre,
                              @RequestParam(required = false) Double price,
                              Model model) {

        List<Book> books;

        if (title != null) {
            books = bookService.searchBooksByTitle(title, Pageable.unpaged()).getContent();
        } else if (author != null) {
            books = bookService.searchBooksByAuthor(author, Pageable.unpaged()).getContent();
        } else if (genre != null) {
            books = bookService.searchBooksByGenre(genre, Pageable.unpaged()).getContent();
        } else if (price != null) {
            books = bookService.searchBooksByPrice(price, Pageable.unpaged()).getContent();
        } else {
            books = bookService.getBookList();
        }

        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        Book book = new Book();
        book.setAuthor(new Author());
        book.setGenre(new Genre());
        model.addAttribute("book", book);
        return "add-book";
    }


    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
