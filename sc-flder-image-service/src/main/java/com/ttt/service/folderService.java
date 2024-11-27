package com.ttt.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ttt.mapper.folderMapper;
import com.ttt.model.file_folder;
import com.ttt.model.image;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class folderService {

    @Autowired
    folderMapper FM;
    @Autowired
    ObjectMapper om;

    @Autowired
    private  ObjectMapper objectMapper;

    @SneakyThrows
    public Integer init() { //注册时调用 创建根目录文件 返回主键和用户关联
        file_folder folder = new file_folder();
        folder.setDirName("root");
        folder.setId(null);

        Map<String, HashMap<Integer,Object>> mp = new HashMap<>();
        mp.put("imageList",new HashMap<>());
        mp.put("folderList",new HashMap<>());
        folder.setData(om.writeValueAsString(mp));

        FM.insert( folder );
        return folder.getId();
    }

    @SneakyThrows
    public file_folder init(file_folder f) {
        f.setId(null);
        Map<String, HashMap<Integer,Object>> mp = new HashMap<>();
        mp.put("imageList",new HashMap<>());
        mp.put("folderList",new HashMap<>());
        f.setData(om.writeValueAsString(mp));

        FM.insert( f );
        return f;
    }

    public file_folder getOne(Integer id){
        return FM.selectById(id);
    }

    public String deleteFolder(Integer id)
    {
        int rows = FM.deleteById(id);
        return rows > 0 ? "Folder deleted successfully" : "Failure to delete folder";
    }

    public Integer updateFolder(file_folder dst)
    {

        return FM.updateById(dst);
    }

    @SneakyThrows
    public void silver_bullet_I (file_folder dst, Object src) //增加
    {
        Map<String, HashMap<Integer,Object>> temp = objectMapper.readValue(dst.getData(), new TypeReference<HashMap<String,HashMap<Integer,Object>>>(){});
        if( src.getClass().getSimpleName().equals("image") )
            temp.get("imageList").put( ((image)src).getId(), src );
        else {
            ((file_folder) src).setData(null);
            temp.get("folderList").put( ((file_folder) src).getId(), src );
        }

        dst.setData( objectMapper.writeValueAsString(temp) );
        updateFolder(dst);
    }

    @SneakyThrows
    @Deprecated
    public void silver_bullet_u (file_folder dst, Object src) //修改
    {
        Object res = null;
        HashMap<String,HashMap<Integer,Object>> meta = objectMapper.readValue(dst.getData(), new TypeReference<HashMap<String,HashMap<Integer,Object>>>(){});;
        if( src.getClass().getSimpleName().equals("image") )
        {
            HashMap<Integer, Object> t = meta.get("imageList");

            HashMap<Integer, image> temp = objectMapper.readValue(objectMapper.writeValueAsString(t), new TypeReference<HashMap<Integer, image>>() {});
            image srcI = (image) src;
//            ( (image)  temp.get("imageList").get( srcI.getId() ) ).setPrefix(srcI.getPrefix());
//            res = temp;
        }
        else {
            HashMap<String,HashMap<Integer,file_folder>> temp = objectMapper.readValue(dst.getData(), new TypeReference<HashMap<String,HashMap<Integer,file_folder>>>(){});
            file_folder srcF = (file_folder) src;
            ( (file_folder)  temp.get("folderList").get( srcF.getId() ) ).setDirName(srcF.getDirName());
//            Iterator<Object> iterator = temp.get("folderList").iterator();
//            while( iterator.hasNext() )
//            {
//                Object obj = iterator.next();
//                if (obj instanceof file_folder) {
//                    file_folder it = (file_folder) obj;
//                    if (  it.getId() == srcF.getId() ) {
//                        it.setDirName(srcF.getDirName());
//                        break;
//                    }
//                }
//            }
            res = temp;
        }

        dst.setData( objectMapper.writeValueAsString(res) );
        updateFolder(dst);
    }

    @SneakyThrows
    public void silver_bullet_D (file_folder dst, Integer id, int type) //删除 type中0表示image 1表示folder
    {
        Map<String,HashMap<Integer,Object>> temp = objectMapper.readValue(dst.getData(), new TypeReference<HashMap<String,HashMap<Integer,Object>>>(){});
        if( type == 0 )
        {
            System.out.println("------------------");
            System.out.println(temp.get("imageList"));
            temp.get("imageList").remove(id);
            System.out.println(temp.get("imageList").get(id));
//            Iterator<Object> iterator = temp.get("imageList").iterator();
//            while( iterator.hasNext() )
//            {
//                Object obj = iterator.next();
//                if (obj instanceof image) {
//                    image it = (image) obj;
//                    if (  it.getId() == id ) {
//                        iterator.remove();
//                        break;
//                    }
//                }
//            }
        }
        else
        {
            temp.get("folderList").remove(id);
//            Iterator<Object> iterator = temp.get("folderList").iterator();
//            while( iterator.hasNext() )
//            {
//                Object obj = iterator.next();
//                if (obj instanceof file_folder) {
//                    file_folder it = (file_folder) obj;
//                    if (  it.getId() == id ) {
//                        iterator.remove();
//                        break;
//                    }
//                }
//            }
        }

        dst.setData( objectMapper.writeValueAsString(temp) );
        updateFolder(dst);
    }

}
