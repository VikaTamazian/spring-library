package org.tamazian.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tamazian.library.dao.LoginDao;
import org.tamazian.library.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements UserDetailsService {

    LoginDao loginDao;

    @Autowired
    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = loginDao.findUserInfo(username);

        if (user == null) {
            throw new UsernameNotFoundException("username was not found in the database");
        }

        List roles = loginDao.getUserRoles(username);

        List grantList = new ArrayList();

        if (roles != null) {
            for (Object role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority((String) role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantList);

        return userDetails;
    }
}
