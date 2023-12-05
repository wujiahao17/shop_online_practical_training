package com.example.backend.convert;

import com.example.backend.bean.User;
import com.example.backend.vo.LoginResultVO;

public interface UserConvert {
    LoginResultVO convertToLoginResultVO(User user);

}
