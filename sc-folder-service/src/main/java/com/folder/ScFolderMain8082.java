package com.folder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName:{ScFolderMain8082}
 * Package:com.folder
 * Description:
 *
 * @Author: yang
 * @Create: 2024/12/3  21:00
 * @Version: 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.folder.mapper")
public class ScFolderMain8082 {
    public static void main(String[] args) {
        SpringApplication.run(ScFolderMain8082.class,args);
    }
}
