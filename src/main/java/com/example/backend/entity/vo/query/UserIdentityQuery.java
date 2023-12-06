package com.example.backend.entity.vo.query;

import lombok.AllArgsConstructor;
import lombok.Data;

//身份认证时传过来的请求
@Data
@AllArgsConstructor
public class UserIdentityQuery {
    private String userName;
    private String userMobile;
}
