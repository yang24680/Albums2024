package com.ttt.service;



import com.ttt.mapper.imageMapper;
import com.ttt.model.image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class imageService {

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
