package com.example.backend.controller;

import com.example.backend.bean.ResultVO;
import com.example.backend.bean.User;
import com.example.backend.query.UserLoginQuery;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //用户注册
    @RequestMapping("/register")
    @ResponseBody
    public ResultVO Register(User user){
        User user1 = new User(null,"wujiahao","wu8321966","小吴","吴家豪","111","15180107827","315776852@qq.com","男",new Date(),new Date(),new Date());
        User registerUser = userService.registerUser(user1);
        //返回一个ResultVO
        ResultVO resultVO = new ResultVO();
        resultVO.setData(registerUser);
        if(registerUser != null){
            resultVO.setStatus("注册成功");
        } else {
            resultVO.setStatus("注册失败");
        }
        return resultVO;
    }

    //用户登录
    @RequestMapping("/login")
    @ResponseBody
    public ResultVO Login(UserLoginQuery userLoginQuery){
        System.out.println("我在这" + userLoginQuery.toString());
        User loginUser = userService.loginUser(userLoginQuery);
        //返回一个ResultVO
        ResultVO resultVO = new ResultVO();
        resultVO.setData(loginUser);
        if(loginUser != null){
            resultVO.setStatus("登录成功");
        } else {
            resultVO.setStatus("登录失败");
        }
        return resultVO;
    }
}
