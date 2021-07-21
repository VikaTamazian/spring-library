package org.tamazian.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.tamazian.library.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List list() {
        String sql = "select username from users";

        List list = jdbcTemplate
                .query(sql, getSqlParameterSource(null, null), new UserMapper());

        return list;
    }

    private PreparedStatementSetter getSqlParameterSource(String username, String password) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (username != null) {
            parameterSource.addValue("username", username);
        }
        if (password != null) {
            parameterSource.addValue("password", password);
        }

        return (PreparedStatementSetter) parameterSource;
    }

    private static final class UserMapper implements RowMapper {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("username"));

            return user;
        }

    }

    public User findUserByUsername(String username) {
        String sql = "select username from users where username = :username";

        List list = jdbcTemplate
                .query(sql, getSqlParameterSource(username, null), new UserMapper());

        return (User) list.get(0);
    }

    public void update(String username, String password) {
        String sql = "update users set password = :password where username = :username";

        jdbcTemplate.update(sql, getSqlParameterSource(username, password));

    }

    public void add(String username, String password) {
        String sql = "insert into users(username, password) values(:username, :password)";
        jdbcTemplate.update(sql, getSqlParameterSource(username, password));

        sql = "insert into user_roles(username, role) values(:username, 'ROLE_USER')";
        jdbcTemplate.update(sql, getSqlParameterSource(username, password));
    }

    public boolean userExists(String username) {
        String sql = "select * from users where username = :username";

        List list = jdbcTemplate
                .query(sql, getSqlParameterSource(username, null), new UserMapper());

        if (list.size() > 0) {
            return true;
        }

        return false;
    }

}