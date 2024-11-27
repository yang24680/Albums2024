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
@TableName("file_folder")
public class file_folder {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String data;
    private String dirName;
    @TableField(exist = false)
    private Integer preDirId;
}