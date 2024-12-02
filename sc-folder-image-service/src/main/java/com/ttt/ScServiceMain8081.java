package com.ttt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName:{ScServiceMain8081}
 * Package:com.ttt
 * Description:
 *
 * @Author: yang
 * @Create: 2024/11/25  17:22
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ttt.mapper")
@EnableFeignClients
public class ScServiceMain8081 {
    public static void main(String[] args) {
        SpringApplication.run(ScServiceMain8081.class,args);
    }
}
