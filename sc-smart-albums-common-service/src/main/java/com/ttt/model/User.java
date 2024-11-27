package com.ttt.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * ClassName:{User}
 * Package:com.ttt.entity
 * Description:
 *
 * @Author: yang
 * @Create: 2024/11/13  15:25
 * @Version: 1.0
 */
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String email;
    private String password;
    private String username;
    private Integer filesId;
}