package com.example.backend.service;

import com.example.backend.entity.vo.query.UserRegisterQuery;

public interface UsersService {
    public boolean RegisterUser(UserRegisterQuery query);
}
