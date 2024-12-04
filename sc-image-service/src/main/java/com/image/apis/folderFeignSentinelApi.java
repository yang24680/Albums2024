package com.image.apis;

import com.ttt.model.SilverBulletDRequest;
import com.ttt.model.SilverBulletIRequest;
import com.ttt.model.file_folder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * ClassName:{folderImageFeignSentinelApi}
 * Package:com.ttt.apis
 * Description:
 *
 * @Author: yang
 * @Create: 2024/12/2  20:49
 * @Version: 1.0
 */
@FeignClient(name = "sc-folder-service",fallback = folderFeignSentinelApiFallback.class)
public interface folderFeignSentinelApi {
    @RequestMapping("/init")
    Integer init();


    @RequestMapping("/getOne")
    file_folder getOne(Integer id);


    @RequestMapping("/silver_bullet_I")
//    void silver_bullet_I(file_folder preF, image temp);
    void silver_bullet_I(@RequestBody SilverBulletIRequest request);

    @RequestMapping("/silver_bullet_D")
    void silver_bullet_D(@RequestBody SilverBulletDRequest request);


}
