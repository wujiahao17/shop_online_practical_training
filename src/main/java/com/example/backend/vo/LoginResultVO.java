package com.example.backend.vo;

import lombok.Data;

@Data
public class LoginResultVO {
    private Integer id;
    private String mobile;
    private String token;
    private String nickname;
    private String avatar;
    private String account;
}