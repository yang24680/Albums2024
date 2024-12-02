package com.ttt.apis;

import org.springframework.stereotype.Component;

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
public class folderImageFeignSentinelApiFallback implements folderImageFeignSentinelApi {
    @Override
    public Integer init() {
        return 0;
    }
}
