package com.image;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName:{ScImageMain8083}
 * Package:com.image
 * Description:
 *
 * @Author: yang
 * @Create: 2024/12/3  21:27
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.image.mapper")
public class ScImageMain8083 {
    public static void main(String[] args) {
        SpringApplication.run(ScImageMain8083.class,args);
    }
}
