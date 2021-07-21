package org.tamazian.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.tamazian.library.entity.Book;

import java.util.List;

@Component
public class BookDao implements AbstractDao<Integer, Book> {
    private final JdbcTemplate jdbcTemplate;

    public static final String DELETE_SQL = """
            DELETE FROM book
            WHERE id = ?
            """;
    public static final String SAVE_SQL = """
            INSERT INTO book(name, author_id, genre_id, isbn)
            VALUES (?,?,?,?)
            """;
    public static final String UPDATE_SQL = """
            UPDATE book 
            SET name = ?,
            author_id = ?,
            genre_id = ?,
            isbn = ?
            WHERE id = ?
            """;
    public static final String FIND_ALL_SQL = """
            SELECT id, name, author_id, genre_id, isbn
            FROM book
            """;
    public static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?  
            """;


    @Autowired
    private BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);

    }

    public void update(Integer id, Book updatedBook) {
        jdbcTemplate.update(UPDATE_SQL, updatedBook.getName(),
                updatedBook.getAuthorId(), updatedBook.getGenreId(), updatedBook.getIsbn(), id);
    }

    public void save(Book book) {
        jdbcTemplate.update(SAVE_SQL, book.getName(), book.getAuthorId(), book.getGenreId(), book.getIsbn());
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

}
