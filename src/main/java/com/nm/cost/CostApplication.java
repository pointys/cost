package com.nm.cost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

//@ServletComponentScan//使用filter需要该扫描 以后费用报表可能会用到过滤器
@EnableCaching//开启缓存 指定redis需加依赖并配置
@SpringBootApplication
public class CostApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CostApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CostApplication.class);
    }
}
