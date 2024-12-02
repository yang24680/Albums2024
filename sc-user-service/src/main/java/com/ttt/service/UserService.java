package com.ttt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ttt.http.Result;
import com.ttt.model.User;
import com.ttt.model.UserDTO;


public interface UserService extends IService<User> {

    public Result register(UserDTO userDTO);

    public Result login(User user1);

}
