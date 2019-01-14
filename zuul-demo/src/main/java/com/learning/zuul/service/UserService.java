package com.learning.zuul.service;


import com.learning.zuul.domain.entity.User;


public interface UserService {

    User findByUsername(String username);

    User findById(Integer id);

}
