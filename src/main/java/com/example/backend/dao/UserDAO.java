package com.example.backend.dao;

import com.example.backend.bean.User;
import com.example.backend.query.UserLoginQuery;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserDAO {
    public int insertUser(User user);
    public User queryByUserName(String userName);
    public User queryByUserNameAndPwd(UserLoginQuery userLoginQuery);
}
