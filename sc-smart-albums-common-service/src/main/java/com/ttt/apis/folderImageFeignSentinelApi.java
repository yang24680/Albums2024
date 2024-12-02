package com.ttt.apis;

import org.springframework.cloud.openfeign.FeignClient;
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
@FeignClient(value = "sc-folder-image-service",fallback = folderImageFeignSentinelApiFallback.class)
public interface folderImageFeignSentinelApi {
    @RequestMapping("init")
    Integer init();
}
