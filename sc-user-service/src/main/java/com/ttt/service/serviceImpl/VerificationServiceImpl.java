package com.ttt.service.serviceImpl;

import cn.hutool.core.util.RandomUtil;
import com.ttt.config.MailProperties;
import com.ttt.http.Result;
import com.ttt.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * ClassName:{VerificationServiceImpl}
 * Package:com.ttt.service.serviceImpl
 * Description:
 *
 * @Author: yang
 * @Create: 2024/12/2  18:03
 * @Version: 1.0
 */

@Service
public class VerificationServiceImpl implements VerificationService {
    @Autowired
    private JavaMailSender mailSender;

    //redis暂存
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //方便配置类属性注入
    @Autowired
    private MailProperties mailProperties;

    private static final long CODE_EXPIRATION = 5; // 验证码有效期（分钟）



    //验证码生成
    public String generateCode() {
        return RandomUtil.randomNumbers(6); // 使用 hutool-all，生成6位随机验证码
    }

    public Result sendVerificationEmail(String email) {

        try {
            //生成验证码
            String code = generateCode();
            System.out.println("生成的验证码：" + code);
            // 存储验证码到Redis，设置有效期
            redisTemplate.opsForValue().set(email, code, CODE_EXPIRATION, TimeUnit.MINUTES);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("你的验证码");
            message.setText("你的验证码是" + code);

            //
            //FORM_EMAIL 对应 application.yaml 中 spring.mail.username
            message.setFrom(mailProperties.getFORM_EMAIL());

            mailSender.send(message);
            return Result.success(code);

        }catch (Exception e){
            return Result.error("邮箱账号格式错误，请重新输入！");
        }
    }

    public boolean verifyCode(String email, String code) {
        String storedCode = redisTemplate.opsForValue().get(email);
        if (storedCode != null && storedCode.equals(code)) {
            redisTemplate.delete(email); // 验证后移除验证码
            return true;
        }
        return false;
    }
}
