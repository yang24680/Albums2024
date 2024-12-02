package com.ttt.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:{TestController}
 * Package:com.ttt.controller
 * Description:
 *
 * @Author: yang
 * @Create: 2024/11/26  18:16
 * @Version: 1.0
 */
@RestController
public class TestController {
    @RequestMapping("/hello")
    @SentinelResource(value = "HelloSentinelResource" ,blockHandler = "HelloBlockHandler")
    public String hello(){

        return "Hello Xiaozhao";
    }
    public String HelloBlockHandler(BlockException e){
        return "sentinel限流喽，服务不可用";
    }



    @RequestMapping("/hello2")
    public String hello2(){

        return "Hello Xiaohong";
    }
}
