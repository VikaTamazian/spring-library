package org.tamazian.library.dao;

import org.tamazian.library.entity.User;

import java.util.List;

public interface LoginDao {

    User findUserInfo(String username);

    List getUserRoles(String username);
}
