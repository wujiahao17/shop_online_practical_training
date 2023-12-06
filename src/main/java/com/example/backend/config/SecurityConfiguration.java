package com.example.backend.config;

import com.example.backend.entity.RestBean;
import com.example.backend.entity.bean.Users;
import com.example.backend.entity.vo.response.AuthorizeVO;
import com.example.backend.filter.JwtAuthorizeFilter;
import com.example.backend.service.UsersService;
import com.example.backend.utils.JWTUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfiguration {
    @Resource
    JWTUtils utils;

    @Resource
    JwtAuthorizeFilter jwtAuthorizeFilter;

    @Resource
    UsersService service;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(conf -> conf
                .requestMatchers("/api/user/register","/api/user/identity","/api/user/resetPwd").permitAll()
                .anyRequest().authenticated()
        ).formLogin(conf -> conf
                .loginProcessingUrl("/api/user/login")
                        .usernameParameter("userName").passwordParameter("userPwd")
                .failureHandler(this::onAuthenticationFailure)
                .successHandler(this::onAuthenticationSuccess)
        ).logout(conf -> conf
                .logoutUrl("/api/user/logout")
                .logoutSuccessHandler(this::onLogoutSuccess)
        ).exceptionHandling(conf -> conf
                .authenticationEntryPoint(this::onUnauthorized)
                .accessDeniedHandler(this::onAccessDeny)
        )
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(conf -> conf
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthorizeFilter, UsernamePasswordAuthenticationFilter.class)
        .build();

    }

    //没有权限
    public void onAccessDeny(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.forbidden(exception.getMessage()).asJsonString());
    }

    //没有token
    public void onUnauthorized(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());
    }

    //登录成功
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        //拿到用户详细信息
        User user = (User) authentication.getPrincipal();
        Users users =  service.findAccountByName(user.getUsername());
        String token = utils.createJwt( user,users.getId(), users.getUserName());
        AuthorizeVO vo = new AuthorizeVO();
        //设置返回的登录成功信息
        vo.setUsername(users.getUserName());
        vo.setToken(token);
        vo.setExpire(utils.expireTime());
        vo.setNickName(users.getNickName());
        vo.setRealName(users.getRealName());
        vo.setUserImg(users.getUserImg());
        vo.setUserMobile(users.getUserMobile());
        vo.setUserSex(users.getUserSex());
        vo.setUserRegtime(users.getUserRegtime());
        response.getWriter().write(RestBean.success(vo).asJsonString());
    }

    //登录失败
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());
    }

    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String authorization = request.getHeader("Authorization");
        if(utils.invalidateJwt(authorization)){
            writer.write(RestBean.success().asJsonString());
        }else {
            writer.write(RestBean.failure(400,"退出登录失败").asJsonString());
        }
    }
}
