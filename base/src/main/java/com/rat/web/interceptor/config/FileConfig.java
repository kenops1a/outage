package com.rat.web.interceptor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 文件路径配置
 * @tip: write_bug
 * @author: yaominc
 * @date: 10/4/2022 下午6:10
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {

    private static final String LOCATION_PATH = "C:\\Users\\Kenopsia\\Desktop\\job\\project\\HostManager\\outage\\fileFolder\\";
    private static final String IMAGE_LOCATION_PATH = "C:\\Users\\Kenopsia\\Desktop\\job\\project\\HostManager\\outage\\imgFolder\\";


    private static final String NET_PATH = "/file/**";
    private static final String IMAGE_NET_PATH = "/img/**";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(NET_PATH).addResourceLocations("file:" + LOCATION_PATH);
        registry.addResourceHandler(IMAGE_NET_PATH).addResourceLocations("file:" + IMAGE_LOCATION_PATH);
    }
}
