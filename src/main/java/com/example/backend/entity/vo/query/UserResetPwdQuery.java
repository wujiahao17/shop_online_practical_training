package com.example.backend.entity.vo.query;

import lombok.AllArgsConstructor;
import lombok.Data;

//修改密码传递的参数
@Data
@AllArgsConstructor
public class UserResetPwdQuery {
    private String userName;
    private String userPwd;
}
