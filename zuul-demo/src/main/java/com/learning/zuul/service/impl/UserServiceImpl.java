package com.learning.zuul.service.impl;



import com.learning.zuul.domain.entity.User;
import com.learning.zuul.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {


    @Override
    public User findByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        Set<String> permissions = new HashSet<>();
        permissions.add("/hello");
//        permissions.add("/noPerm");
        user.setPermissions(permissions);
        return user;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }
}
