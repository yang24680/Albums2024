package com.ttt.apis;

import com.ttt.http.Result;
import com.ttt.model.User;
import com.ttt.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * ClassName:{UserFeignSentinelApi}
 * Package:com.ttt.apis
 * Description:
 *
 * @Author: yang
 * @Create: 2024/11/26  22:37
 * @Version: 1.0
 */
@FeignClient(value = "sc-user-service",fallback = UserFeignSentinelApiFallback.class)
public interface UserFeignSentinelApi {
    //发送验证码
    @PostMapping("/send")
    public Result sendVerificationCode(@RequestBody Map<String,String> mp);


    //    用户注册username，password，code
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO);


    //    用户登录username,password
    @PostMapping("/login")
    public Result login(@RequestBody User user);
}
