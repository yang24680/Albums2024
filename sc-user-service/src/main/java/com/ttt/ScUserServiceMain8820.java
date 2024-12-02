package com.ttt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * ClassName:{ScUserServiceMain}
 * Package:com.ttt
 * Description:
 *
 * @Author: yang
 * @Create: 2024/11/25  10:53
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.ttt.mapper")
public class ScUserServiceMain8820 {
    public static void main(String[] args) {
        SpringApplication.run(ScUserServiceMain8820.class,args);
    }
}
