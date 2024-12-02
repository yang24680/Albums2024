package com.ttt.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ttt.http.Result;
import com.ttt.model.User;
import com.ttt.model.UserDTO;
import com.ttt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


//    用户注册username，password，code
    @PostMapping("/register")
    @SentinelResource(value = "RegisterSentinelResource" ,blockHandler = "RegisterBlockHandler")
    public Result register(@RequestBody UserDTO userDTO) {
            return userService.register(userDTO);
    }
    public Result RegisterBlockHandler(@RequestBody UserDTO userDTO){
        Result result =new Result();
        result.setMsg("fail，注册失败，流量高峰，sentinel限流");
        result.setData(userDTO);
        return result;
    }


//    用户登录username,password
    @PostMapping("/login")
    @SentinelResource(value = "LoginSentinelResource" ,blockHandler = "LoginBlockHandler")
    public Result login(@RequestBody User user) {
        // 用户登录
        return userService.login(user);
    }
    public Result LoginBlockHandler(@RequestBody User user){
        Result result =new Result();
        result.setMsg("fail，登陆失败，流量高峰，sentinel限流");
        result.setData(user);
        return result;
    }


}