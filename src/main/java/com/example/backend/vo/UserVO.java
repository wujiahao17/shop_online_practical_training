package com.example.backend.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserVO {
    private String token;
    private Integer Id;
    private String mobile;
    private String nickname;
    private String avatar;
    private String account;
    private Integer gender;
    private Timestamp birthday;
    private String profession;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
}