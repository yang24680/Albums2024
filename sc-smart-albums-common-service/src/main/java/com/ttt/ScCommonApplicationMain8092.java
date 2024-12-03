package com.ttt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * ClassName:{ScCommonApplicationMain8091}
 * Package:com.ttt
 * Description:
 *
 * @Author: yang
 * @Create: 2024/11/25  10:01
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ScCommonApplicationMain8092 {
    public static void main(String[] args) {
            SpringApplication.run(ScCommonApplicationMain8092.class,args);
    }
}
