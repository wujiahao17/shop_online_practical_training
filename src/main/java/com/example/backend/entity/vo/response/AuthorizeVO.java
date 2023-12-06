package com.example.backend.entity.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorizeVO {
    private String username;
    private String token;
    private Date expire;//token过期时间
    private String nickName;
    private String realName;
    private String userImg;
    private String userMobile;
    private String userSex;
    private Date userRegtime;
}
