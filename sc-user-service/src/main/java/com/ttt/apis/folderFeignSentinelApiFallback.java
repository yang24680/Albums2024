package com.ttt.apis;

import org.springframework.stereotype.Component;


@Component
public class folderFeignSentinelApiFallback implements folderFeignSentinelApi {
    @Override
    public Integer init() {
        return 0;
    }

//    @Override
//    public file_folder getOne(Integer id) {
//        return null;
//    }
//
//    @Override
//    public void silver_bullet_I(@RequestBody SilverBulletIRequest request) {
//
//    }
//
//    @Override
//    public void silver_bullet_D(@RequestBody SilverBulletDRequest request) {
//
//    }
}
