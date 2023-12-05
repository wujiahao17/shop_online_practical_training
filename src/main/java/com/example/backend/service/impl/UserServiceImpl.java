package com.example.backend.service.impl;

import com.example.backend.bean.User;
import com.example.backend.convert.UserConvert;
import com.example.backend.dao.UserDAO;
import com.example.backend.query.UserLoginQuery;
import com.example.backend.service.UserService;
import com.example.backend.utils.JWTUtils;
import com.example.backend.vo.LoginResultVO;
import com.example.backend.vo.UserTokenVO;
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
//            LoginResultVO userVO = UserConvert.INSTANCE.convertToLoginResultVO(user);
            // 4、生成token，存入redis并设置过期时间
//            UserTokenVO tokenVO = new UserTokenVO(userVO.getId());
//            String token = JWTUtils.generateToken("shop_online", tokenVO.toMap());
//            redisService.set(APP_NAME + userVO.getId(), token, APP_TOKEN_EXPIRE_TIME);
//            userVO.setToken(token);
        }
        //登录失败
        System.out.println("登录失败");
        return null;
    }
}
