package com.example.bookstore.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bookstore.service.BookService;

@Controller
public class BookViewController {

    private final BookService bookService;

    public BookViewController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String showBooks(Model model){
        model.addAttribute("books", bookService.getBookList());
        return "books";
    }
}
