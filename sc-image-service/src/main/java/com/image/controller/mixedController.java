package com.image.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.image.apis.folderFeignSentinelApi;
import com.image.service.imageService;
import com.ttt.http.Result;
import com.ttt.model.SilverBulletDRequest;
import com.ttt.model.SilverBulletIRequest;
import com.ttt.model.file_folder;
import com.ttt.model.image;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;

@RestController
public class mixedController {

    @Autowired
    imageService IS;


//    @Autowired
//    folderService FS;

    @Resource
    private folderFeignSentinelApi FS;

    @Autowired
    private  ObjectMapper objectMapper;

    @SneakyThrows
    @RequestMapping("/getImageByDirId")
    public Result getImage(@RequestBody Map<String,Integer> mp)
    {

        Map<String,HashMap<String, image>> map = objectMapper.readValue( FS.getOne(mp.get("id")).getData(),  new TypeReference<HashMap<String,HashMap<String,image>>>(){} );

        Map<String,Collection<Object>> res = new HashMap<>();
        Collection<Object> iList = new ArrayList<>();

        map.get("imageList").forEach( (k,v) -> {
            try {

                image e = v;
                File file = new File(e.getSrc());
                byte[] fileBytes = new byte[(int) file.length()];
                try (FileInputStream fis = new FileInputStream(file)) {
                    fis.read(fileBytes);
                }
                e.setBase64( Base64.getEncoder().encodeToString(fileBytes) );
                iList.add(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        res.put("imageList", iList);
        System.out.println( objectMapper.writeValueAsString(Result.success(res)) );

        return Result.success(res);
    }

    @SneakyThrows
    @RequestMapping("/getFolderByDirId")
    public Result getFolder(@RequestBody Map<String,Integer> mp)
    {
        Map<String,HashMap<String, file_folder>> map = objectMapper.readValue( FS.getOne(mp.get("id")).getData(),  new TypeReference<HashMap<String,HashMap<String,file_folder>>>(){} );

        Map<String,Collection<Object>> res = new HashMap<>();
        Collection<Object> fList = new ArrayList<>();

        map.get("folderList").forEach( (k,v) -> {
            System.out.println(v);

            file_folder e = (file_folder) v;
            e.setData(null);
            fList.add(e);
        });

        res.put("folderList", fList);
        return Result.success(res);
    }


    static String file_path = ".\\src\\main\\resources\\uploadedFile\\";

    @SneakyThrows
    @RequestMapping("/uploadImage")
    public Result insertImage(@RequestBody Map<String, Collection<image>> meta)
    {
        Collection<image> data =  meta.get("imageList");

        Collection<image> images = new ArrayList<>();

        data.forEach( value -> {
//            image e;
//            try {
//                e = objectMapper.readValue(value.toString(), image.class);
//            } catch (JsonProcessingException ex) {
//                throw new RuntimeException(ex);
//            }
            byte[] file_data = decodeBase64( value.getBase64() );
            value.setBase64(null);
            String file_name = value.getPrefix();

            for( int i = 0; i < 1000; ++ i )
            {
                Path path = Paths.get( file_path,file_name + i + '.' + value.getSuffix() );
                if( ! Files.exists(path) ){
                    value.setSrc(file_path + file_name + i + '.' + value.getSuffix());
                    break;
                }
            }

            Path path = Paths.get(value.getSrc());

            try {
                Files.createDirectories(path.getParent());
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path.toFile()));
                bufferedOutputStream.write(file_data);
            } catch (IOException ex) {
                System.out.println("Failure to upload a image");
                throw new RuntimeException(ex);
            }
            image temp = IS.insert(value);
            images.add( temp );

            file_folder pre_f = FS.getOne(value.getPreDirId());

            SilverBulletIRequest silverBulletIRequest = new SilverBulletIRequest();
            silverBulletIRequest.setPreF(pre_f);
            silverBulletIRequest.setObj(temp);

            FS.silver_bullet_I(silverBulletIRequest);
        });

        System.out.println( objectMapper.writeValueAsString(Result.success(images)) );

        return Result.success(images);
    }

    @RequestMapping("/updateImage")
    public Result updateImageName(@RequestBody Map<String, Collection<image>> meta)
    {
        Collection<image> q = meta.get("imageList");
        q.forEach( (v) -> {
            System.out.println(v);
            IS.updateName(v);
            file_folder pre_f =  FS.getOne( v.getPreDirId() );
            image img_back = IS.getOne(v.getId());
            img_back.setPrefix(v.getPrefix());

//            整合openfeign 使用对象  request 请求
            SilverBulletDRequest request = new SilverBulletDRequest();
            request.setPreF(pre_f);
            request.setId(v.getId());
            request.setType(0);


            FS.silver_bullet_D(request);

            SilverBulletIRequest silverBulletIRequest = new SilverBulletIRequest();
            silverBulletIRequest.setPreF(pre_f);
            silverBulletIRequest.setObj(img_back);

            FS.silver_bullet_I(silverBulletIRequest);
//            FS.silver_bullet_I(pre_f, img_back);
        });
 
        return Result.success();
    }

    @RequestMapping("/deleteImage")
    public  Result deleteImage(@RequestBody  Map<String, Collection<image>> meta)
    {
        Collection<image> data = meta.get("imageList");
        data.forEach(e -> {
            System.out.println("e:=======");
            System.out.println(e);
            Integer id = e.getId();
//            Path path = Paths.get(IS.get_src(id));

            System.out.println(id);
            IS.deleteImage(id);
            file_folder pre_f =  FS.getOne( e.getPreDirId() );

//            整合openfeign 使用对象  request 请求

            SilverBulletDRequest request = new SilverBulletDRequest();
            request.setPreF(pre_f);
            request.setId(id);
            request.setType(0);

            FS.silver_bullet_D(request);


//            try {
//                boolean deleted = Files.deleteIfExists(path);
//                if (deleted) {
//                    System.out.println("文件删除成功！");
//                } else {
//                    System.out.println("文件不存在，未执行删除操作。");
//                }
//            } catch (IOException ex) {
//                System.out.println("删除文件时发生错误：" + ex.getMessage());
//            }
        });


        return Result.success();
    }

}
