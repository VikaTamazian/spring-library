package org.tamazian.library.service;

import org.tamazian.library.entity.User;

import java.util.List;

public interface UserService {

    public List list();

    public User findUserByUsername(String username);

    public void update(String username, String password);

    public void add(String username, String password);

    public boolean userExists(String username);
}