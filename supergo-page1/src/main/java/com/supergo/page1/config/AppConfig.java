package com.supergo.page1.config;

import com.supergo.page1.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器认证配置类
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/*.html","/**/*.css","/**/*.js","/**/*.png","/**/*.jpg","/**/*.json")
                .excludePathPatterns("/**/*.eto","/**/*.svg","/**/*.ttf","/**/*.woff");
    }
}
