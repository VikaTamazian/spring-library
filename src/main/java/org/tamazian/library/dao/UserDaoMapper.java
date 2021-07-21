package org.tamazian.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.tamazian.library.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        var username = resultSet.getString("username");
        var password = resultSet.getString("password");

        return new User(username, password);
    }
}
