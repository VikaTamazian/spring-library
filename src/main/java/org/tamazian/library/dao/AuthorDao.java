package org.tamazian.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.tamazian.library.entity.Author;
import org.tamazian.library.entity.Person;

import java.util.List;

@Component
public class AuthorDao implements AbstractDao<Integer, Author> {
    private final JdbcTemplate jdbcTemplate;

    public static final String DELETE_SQL = """
            DELETE  FROM author
            WHERE id = ?
            """;

    public static final String SAVE_SQL = """
            INSERT INTO author(author_name)
            VALUES (?)
            """;

    public static final String UPDATE_SQL = """
            UPDATE author
            SET author_name = ?
            WHERE id = ?
            """;

    public static final String FIND_ALL_SQL = """
            SELECT id, author_name
            FROM author
            """;

    public static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;


    @Autowired
    public AuthorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Author> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(Author.class));
    }

    public Author findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Author.class))
                .stream().findAny().orElse(null);
    }

    public void save(Author author) {
        jdbcTemplate.update(SAVE_SQL, author.getAuthorName());
    }

    public void update(Integer id, Author updatedAuthor) {
        jdbcTemplate.update(UPDATE_SQL, updatedAuthor.getAuthorName(), id);
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

}