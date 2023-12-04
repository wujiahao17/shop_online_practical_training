package com.example.backend.service.impl;

import com.example.backend.bean.User;
import com.example.backend.dao.UserDAO;
import com.example.backend.query.UserLoginQuery;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public User registerUser(User user) {
        if(userDAO.queryByUserName(user.getUserName()) == null){
            int i = userDAO.insertUser(user);
            if(i > 0){
                return user;
            }
        }
        return null;
    }

    @Override
    public User loginUser(UserLoginQuery userLoginQuery) {
        User user = userDAO.queryByUserNameAndPwd(userLoginQuery);
        if(user != null){
            //登录成功
            System.out.println("登录成功");
        }
        //登录失败
        System.out.println("登录失败");
        return null;
    }
}
