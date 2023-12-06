package com.example.backend.entity.vo.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterQuery {
    private String userName;
    private String userPwd;
}
