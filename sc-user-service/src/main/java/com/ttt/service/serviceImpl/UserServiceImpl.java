package com.ttt.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ttt.apis.folderImageFeignSentinelApi;
import com.ttt.http.Result;
import com.ttt.mapper.UserMapper;
import com.ttt.model.User;
import com.ttt.model.UserDTO;
import com.ttt.service.UserService;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * ClassName:{UserServiceImpl}
 * Package:com.ttt.service.serviceImpl
 * Description:
 *
 * @Author: yang
 * @Create: 2024/12/2  18:02
 * @Version: 1.0
 */
@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private UserMapper userMapper;
//    @Autowired
//    private com.ttt.service.folderService folderService;
    @Resource
    private folderImageFeignSentinelApi folderService;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public Result register(UserDTO userDTO) {


        // 验证码校验逻辑（使用Redis存储）
        String storedCode = redisTemplate.opsForValue().get(userDTO.getEmail());
        System.out.println("###########\t这是registerService内部，后边是验证码:\t" + storedCode);
        System.out.println("###########\t email"+ userDTO.getEmail()+"\n"+userDTO.getPassword()+"\n"+userDTO.getCode());

        if (storedCode != null && storedCode.equals(userDTO.getCode())) {

            redisTemplate.delete(userDTO.getEmail()); // 验证后移除验证码
            // 创建用户实体
            User user = new User();
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setFilesId( folderService.init() );
//            System.out.println("@@@@@@@@@@@@@@@@@@@@@");
//            System.out.println(user.getUserId());
//            System.out.println(user.getEmail());
//            System.out.println(user.getPassword());
            // 保存用户信息到数据库
            userMapper.insert(user);

            return Result.success("注册成功，你的账号为:" + user.getEmail());
        }
        return Result.error("注册失败，验证码错误");
    }


    public Result login(User user1) {
        try {
            //根据用户 email 查询 user
            User user = userMapper.selectByEmail(user1.getEmail());

            System.out.println("根据用户 email 查询 user数据库对应登录信息:\nuser.getEmail() = " + user.getEmail() + ", user.getPassword() = " + user.getPassword());

            //        //待完成：使用 security
            //        if (!passwordEncoder.matches(password, user.getPassword())) {
            //            return "密码错误";
            //        }

            if (!user1.getPassword().equals(user.getPassword())) {
                return Result.error("密码错误");
            }
            return Result.success( user.getFilesId());

        }catch (Exception e){
            return Result.error("账号未注册，请注册后再登陆。");
        }
    }
}
