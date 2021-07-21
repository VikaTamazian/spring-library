package org.tamazian.library.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private Integer id;
    @NotEmpty(message = "This field should not be empty")
    private String name;
    @NotEmpty(message = "This field should not be empty")
    private Author authorId;
    @NotEmpty(message = "This field should not be empty")
    private Genre genreId;
    @Size(min = 6, max = 10, message = "ISBN should be between 6 and 10")
    private String isbn;

    public Book(Integer id, String name, Author authorId, Genre genreId, String isbn) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
        this.isbn = isbn;
    }

    public Book() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }

    public Genre getGenreId() {
        return genreId;
    }

    public void setGenreId(Genre genreId) {
        this.genreId = genreId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", authorId=" + authorId +
               ", genreId=" + genreId +
               ", isbn='" + isbn + '\'' +
               '}';
    }
}
