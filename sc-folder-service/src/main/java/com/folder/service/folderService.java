package com.folder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttt.model.SilverBulletDRequest;
import com.ttt.model.file_folder;

public interface folderService extends IService<file_folder> {

    Integer init();

    file_folder init(file_folder f);

    file_folder getOne(Integer id);

    String deleteFolder(Integer id);

    Integer updateFolder(file_folder dst);

    void silver_bullet_I (file_folder dst, Object src);


    void silver_bullet_u (file_folder dst, Object src);


//    void silver_bullet_D(SilverBulletDRequest request);

    void silver_bullet_D(file_folder preF, Integer id, int type);
}