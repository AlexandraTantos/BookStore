package com.example.bookstore.views;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Genre;
import com.example.bookstore.service.AuthorService;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.GenreService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookViewController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookViewController(BookService bookService, AuthorService authorService,GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
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

        if (title != null && !title.isBlank()) {
            books = bookService.searchBooksByTitle(title, Pageable.unpaged()).getContent();
        } else if (author != null && !author.isBlank()) {
            books = bookService.searchBooksByAuthor(author, Pageable.unpaged()).getContent();
        } else if (genre != null && !genre.isBlank()) {
            books = bookService.searchBooksByGenre(genre, Pageable.unpaged()).getContent();
        } else if (price != null ) {
            books = bookService.searchBooksByPrice(price, Pageable.unpaged()).getContent();
        } else {
            books = bookService.getBookList();
        }

        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);

        List<Author> authors = authorService.getAllAuthors(Pageable.unpaged()).getContent();
        List<Genre> genres = genreService.getAllGenres(Pageable.unpaged()).getContent();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);

        return "add-book";
    }



    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        Author author = authorService.getAuthorById(book.getAuthor().getId());
        Genre genre = genreService.getGenreById(book.getGenre().getId());

        book.setAuthor(author);
        book.setGenre(genre);

        bookService.addBook(book);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
