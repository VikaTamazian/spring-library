package org.tamazian.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.tamazian.library.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LoginDaoImpl implements LoginDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findUserInfo(String username) {
        String sql = "select username,password from users where username = :username";

        User user = (User) jdbcTemplate
                .queryForObject(sql, new SqlParameterSource[]{getSqlParameterSource(username, "")}, new UserInfoMapper());

        return user;
    }

    private static final class UserInfoMapper implements RowMapper {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            String username = rs.getString("username");
            String password = rs.getString("password");
            return new User(username, password);
        }

    }

    private SqlParameterSource getSqlParameterSource(String username, String password) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", username);
        parameterSource.addValue("password", password);

        return parameterSource;
    }

    public List getUserRoles(String username) {
        String sql = "select role from user_roles where username = :username";

        List roles = jdbcTemplate
                .queryForList(sql, getSqlParameterSource(username, ""), String.class);

        return roles;
    }

}