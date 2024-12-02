package com.ttt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttt.model.image;
import org.springframework.stereotype.Service;

@Service
public interface imageService extends IService<image> {

    image insert(image e);

    String get_src(Integer id);

    image getOne(Integer id);

    Integer updateName(image dst);
    String deleteImage(Integer id);


}
