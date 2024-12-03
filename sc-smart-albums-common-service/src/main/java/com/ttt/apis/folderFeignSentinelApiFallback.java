package com.ttt.apis;

import com.ttt.model.file_folder;
import com.ttt.model.image;
import org.springframework.stereotype.Component;

import java.awt.color.ICC_Profile;

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
    public void silver_bullet_I(file_folder preF, image temp) {

    }

    @Override
    public void silver_bullet_D(file_folder preF, Integer id, int i) {

    }
}
