package com.zjut.qll.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/advance/index").setViewName("advance/index");
        registry.addViewController("/admin/index").setViewName("admin/index");
//        registry.addRedirectViewController("/admin/index","/admin/index");
        registry.addViewController("/user/index").setViewName("user/index");
        registry.addViewController("/user/workbench").setViewName("user/workbench");
        registry.addViewController("/user/task").setViewName("user/task");
        registry.addViewController("/user/documents").setViewName("user/documents");
    }
}
