package com.zjut.qll.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("login");
        registry.addViewController("/user/register").setViewName("register");
        registry.addViewController("/advance/index").setViewName("advance/workbench");
        registry.addViewController("/advance/workbench").setViewName("advance/workbench");
        registry.addViewController("/admin/index").setViewName("admin/index");
//        registry.addRedirectViewController("/admin/index","/admin/index");
        registry.addViewController("/user/index").setViewName("user/index");
        registry.addViewController("/user/workbench").setViewName("user/workbench");
        registry.addViewController("/user/task").setViewName("user/task");
        registry.addViewController("/user/documents").setViewName("user/documents");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/user/login","/user/toRegister","/user/register",
                                     "/css/**","/js/**", "/img/**","/font-awesome/**","/fonts/**");
    }
}
