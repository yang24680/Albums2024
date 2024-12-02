package com.ttt.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ttt.mapper.imageMapper;
import com.ttt.model.image;
import com.ttt.service.imageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:{imageServicceImpl}
 * Package:com.ttt.service.serviceImpl
 * Description:
 *
 * @Author: yang
 * @Create: 2024/12/2  20:34
 * @Version: 1.0
 */
@Service
public class imageServiceImpl extends ServiceImpl<imageMapper,image> implements imageService {
    @Autowired
    private imageMapper IM;

    public image insert(image e)
    {
        e.setId(null);
        IM.insert(e);
        return e;
    }

    public String get_src(Integer id)
    {
        return IM.selectById(id).getSrc();
    }
    public image getOne(Integer id)
    {
        return IM.selectById(id);
    }

    public Integer updateName(image dst)
    {
        return IM.updateById(dst);
    }

    public String deleteImage(Integer id)
    {
        int rows = IM.deleteById(id);
        return rows > 0 ? "Image deleted successfully" : "Failure to delete image";
    }
}
