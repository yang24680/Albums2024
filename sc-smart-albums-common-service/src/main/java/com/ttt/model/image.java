package com.ttt.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("image")
public class image {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String prefix;
    private  String suffix;
    private String src;
    @TableField(exist = false)
    private String base64;
    @TableField(exist = false)
    private Integer preDirId;
}
