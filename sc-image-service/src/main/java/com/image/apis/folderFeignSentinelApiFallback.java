package com.image.apis;

import com.ttt.model.SilverBulletDRequest;
import com.ttt.model.SilverBulletIRequest;
import com.ttt.model.file_folder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName:{folderImageFeignSentinelApiFallback}
 * Package:com.ttt.apis
 * Description:
 *
 * @Author: yang
 * @Create: 2024/12/2  21:34
 * @Version: 1.0
 */

@Component
public class folderFeignSentinelApiFallback implements folderFeignSentinelApi {
    @Override
    public Integer init() {
        return 0;
    }

    @Override
    public file_folder getOne(Integer id) {
        return null;
    }

    @Override
    public void silver_bullet_I(@RequestBody SilverBulletIRequest request) {

    }

    @Override
    public void silver_bullet_D(@RequestBody SilverBulletDRequest request) {

    }
}
