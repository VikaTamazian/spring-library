package org.tamazian.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tamazian.library.dao.UserDao;
import org.tamazian.library.entity.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List list() {
        return userDao.list();
    }

    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public void update(String username, String password) {
        userDao.update(username, passwordEncoder.encode(password));
    }

    public void add(String username, String password) {
        userDao.add(username, passwordEncoder.encode(password));
    }

    public boolean userExists(String username) {
        return userDao.userExists(username);
    }

}
