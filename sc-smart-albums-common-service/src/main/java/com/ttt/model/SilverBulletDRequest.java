package com.ttt.model;

import lombok.Data;

/**
 * ClassName:{SilverBulletDRequest}
 * Package:com.ttt.model
 * Description:
 *
 * @Author: yang
 * @Create: 2024/12/4  9:22
 * @Version: 1.0
 */
@Data
// 定义一个新的请求对象来封装参数
public  class SilverBulletDRequest {
//    file_folder dst, Integer id, int type) //删除 type中0表示image 1表示folder
    private file_folder preF;
    private Integer id;
    private int type;

    // 省略构造器、getter和setter方法
}