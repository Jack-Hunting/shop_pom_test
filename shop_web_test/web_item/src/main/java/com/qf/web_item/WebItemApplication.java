package com.qf.web_item;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qf")
@DubboComponentScan("com.qf.controller")
public class WebItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebItemApplication.class, args);
    }

}
