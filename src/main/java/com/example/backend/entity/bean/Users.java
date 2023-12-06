package com.example.backend.entity.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@TableName("users")
@AllArgsConstructor
public class Users {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userName;
    private String userPwd;
    private String nickName;
    private String realName;
    private String userImg;
    private String userMobile;
    private String userSex;
    private Date userRegtime;
}
