package com.nm.cost.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 韩老魔
 * 实现WebMvcConfigurer接口配置拦截器
 * @Date: 2019/2/27 0027 12:05
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor interceptor;

    //登录拦截
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(interceptor);

        //拦截路径
        addInterceptor.addPathPatterns("/**");

        //排除路径
        addInterceptor.excludePathPatterns("/error");
        //登录http://localhost:8081/cost/system/login
        //登录验证http://localhost:8081/cost/system/doLogin
        List<String> excludeUrl = new ArrayList<>();
        excludeUrl.add("/system/login");
        excludeUrl.add("/resource/**");
        addInterceptor.excludePathPatterns(excludeUrl);

    }

    //控制层请求编码
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}
