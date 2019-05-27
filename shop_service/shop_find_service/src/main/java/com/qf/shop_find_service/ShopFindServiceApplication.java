package com.qf.shop_find_service;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qf")
@DubboComponentScan("com.qf.serviceImpl")
public class ShopFindServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopFindServiceApplication.class, args);
    }

}
