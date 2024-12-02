package com.ttt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttt.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    int insert(User user);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User selectByEmail(String email);
}