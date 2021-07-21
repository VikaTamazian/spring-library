package org.tamazian.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.tamazian.library.entity.Genre;

import java.util.List;

@Component
public class GenreDao implements AbstractDao<Integer, Genre> {

    private final JdbcTemplate jdbcTemplate;

    public static final String DELETE_SQL = """
            DELETE  FROM genre
            WHERE id = ?
            """;

    public static final String SAVE_SQL = """
            INSERT INTO genre(genre_name)
            VALUES (?)
            """;

    public static final String UPDATE_SQL = """
            UPDATE genre 
            SET genre_name = ?
            WHERE id = ?
            """;
    public static final String FIND_ALL_SQL = """
            SELECT id, genre_name
            FROM genre
            """;

    public static final String FIND_BY_ID_SQL = """
            %sWHERE id = ?""".formatted(FIND_ALL_SQL);


    @Autowired
    public GenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Genre> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(Genre.class));
    }

    public Genre findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Genre.class))
                .stream().findAny().orElse(null);
    }

    public void save(Genre genre) {
        jdbcTemplate.update(SAVE_SQL, genre.getGenreName());
    }

    public void update(Integer id, Genre updatedGenre) {
        jdbcTemplate.update(UPDATE_SQL, updatedGenre.getGenreName(), id);
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

}