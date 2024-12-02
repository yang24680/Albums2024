package com.ttt.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;


@Data
public class UserDTO {
    private String email;
    private String password;
    @TableField(exist = false)
    private String code;
}