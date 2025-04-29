package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title;

    @ManyToOne
    @JsonManagedReference
    private Author author;
    
    @ManyToOne
    private Genre genre;

    @NotNull
    private double price;

    public Book(){
    }

    public Book(String title, Author author, Genre genre, double price){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Author geAuthor(){
        return author;
    }

    public void setAuthor(Author author){
        this.author = author;
    }

    public Genre getGenre(){
        return genre;
    }

    public void setGenre(Genre genre){
        this.genre = genre;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

}
