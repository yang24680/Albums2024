package com.ttt.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ttt.http.Result;
import com.ttt.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    //发送验证码
    @PostMapping("/send")
    @SentinelResource(value = "sendVerificationCodeSentinelResource",blockHandler = "sendVerificationCodeBlockHandler")
    public Result sendVerificationCode(@RequestBody Map<String,String> mp) {
        String email =  mp.get("email");
        System.out.println("#############   验证码  来喽！！！！！");
        return verificationService.sendVerificationEmail(email);
    }
    public Result sendVerificationCodeBlockHandler(@RequestBody Map<String,String> mp){
        Result result =new Result();
        result.setMsg("fail， 发送验证码失败，sentinel限流");
        return result;
    }

    //验证验证码    做测试
    @PostMapping("/verify")
    public Result verifyCode(@RequestParam String email, @RequestParam String code) {
        boolean isValid = verificationService.verifyCode(email, code);
        if (isValid) {
            return Result.success(null);
        } else {
            return Result.error("验证码错误，请重新输入");
        }
    }

}