package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.bean.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}
