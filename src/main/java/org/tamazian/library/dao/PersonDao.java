package org.tamazian.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.tamazian.library.entity.Person;

import java.util.List;

@Component
public class PersonDao implements AbstractDao<Integer, Person> {

    private final JdbcTemplate jdbcTemplate;
    public static final String FIND_ALL_SQL = """
            SELECT id, name, login, email FROM person
            """;
    public static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id=?                 
            """;
    public static final String SAVE_SQL = """
            INSERT INTO person(name, login, email) 
            VALUES(?, ?, ?)
            """;
    public static final String UPDATE_SQL = """
            UPDATE person SET name=?, login=?, email=?
            WHERE id=?
            """;
    public static final String DELETE_SQL = """
            DELETE FROM person
            WHERE id=? 
            """;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(Person.class));
    }

    public Person findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update(SAVE_SQL, person.getName(), person.getLogin(),
                person.getEmail());
    }

    public void update(Integer id, Person updatedPerson) {
        jdbcTemplate.update(UPDATE_SQL, updatedPerson.getName(),
                updatedPerson.getLogin(), updatedPerson.getEmail(), id);
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }
}

