//package com.ttt.apis;
//
//import com.ttt.http.Result;
//import com.ttt.model.User;
//import com.ttt.model.UserDTO;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
// * ClassName:{UserFeignSentinelApiFallback}
// * Package:com.ttt.apis
// * Description:
// *
// * @Author: yang
// * @Create: 2024/11/26  22:43
// * @Version: 1.0
// */
//@Component
//public class UserFeignSentinelApiFallback implements UserFeignSentinelApi{
//    @Override
//    public Result sendVerificationCode(Map<String, String> mp) {
//        Result result = new Result();
//        result.setMsg("验证码服务宕机，fallback降级");
//        return result;
//    }
//
//    @Override
//    public Result register(UserDTO userDTO) {
//        Result result = new Result();
//        result.setMsg("注册服务宕机，fallback降级");
//        return result;
//    }
//
//    @Override
//    public Result login(User user) {
//        Result result = new Result();
//        result.setMsg("登陆服务宕机，fallback降级");
//        return result;
//    }
//}
