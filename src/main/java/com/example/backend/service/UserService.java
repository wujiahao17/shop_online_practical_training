package com.example.backend.service;

import com.example.backend.bean.User;
import com.example.backend.query.UserLoginQuery;

public interface UserService {
    public User registerUser(User user);
    public User loginUser(UserLoginQuery userLoginQuery);
}
