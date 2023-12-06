package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Account;
import com.example.backend.entity.bean.Users;
import com.example.backend.entity.vo.query.UserRegisterQuery;
import com.example.backend.mapper.AccountMapper;
import com.example.backend.mapper.UsersMapper;
import com.example.backend.service.UsersService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Override
    public boolean RegisterUser(UserRegisterQuery userRegisterQuery) {
        if((query().eq("user_name",userRegisterQuery.getUserName()).one()) == null){
            String Pwd = new BCryptPasswordEncoder().encode(userRegisterQuery.getUserPwd());
            Users users = new Users(null,userRegisterQuery.getUserName(),Pwd,null,null,"https://hbimg.huabanimg.com/07b029c67010c0a17a1c78fcbc92ce612de4cf3ae8dd-Oc4KXC_fw658",null,null,new Date());
            baseMapper.insert(users);
            return true;
        }
        return false;
    }
}
