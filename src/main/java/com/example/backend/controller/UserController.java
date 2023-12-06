package com.example.backend.controller;

import com.example.backend.entity.RestBean;
import com.example.backend.entity.vo.query.UserIdentityQuery;
import com.example.backend.entity.vo.query.UserRegisterQuery;
import com.example.backend.entity.vo.query.UserResetPwdQuery;
import com.example.backend.service.UsersService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UsersService usersService;

    @PostMapping("/register")
    @ResponseBody
    public void Register(UserRegisterQuery query, HttpServletResponse response) throws IOException {
        boolean state = usersService.RegisterUser(query);
        response.setContentType("application/json;charset=utf-8");
        if(state){
            response.getWriter().write(RestBean.success().asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("注册失败").asJsonString());
        }
    }

    @PostMapping("/identity")
    @ResponseBody
    public void IdentityAuthentication(UserIdentityQuery query, HttpServletResponse response) throws IOException {
        boolean state = usersService.IdentityAuthentication(query);
        response.setContentType("application/json;charset=utf-8");
        if(state){
            response.getWriter().write(RestBean.success().asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("填写错误").asJsonString());
        }
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    public void ResetPassword(UserResetPwdQuery query, HttpServletResponse response) throws IOException {
        boolean state = usersService.ResetPassword(query);
        response.setContentType("application/json;charset=utf-8");
        if(state){
            response.getWriter().write(RestBean.success().asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("密码修改错误").asJsonString());
        }
    }


}
