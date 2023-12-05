package com.example.backend.convert.impl;

import com.example.backend.bean.User;
import com.example.backend.convert.UserConvert;
import com.example.backend.vo.LoginResultVO;

public class UserConvertImpl implements UserConvert {

    @Override
    public LoginResultVO convertToLoginResultVO(User user) {
        if(user == null) {
            return null;
        }
        //需要补充
        return null;
    }
}
