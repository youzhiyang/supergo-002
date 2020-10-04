package com.supergo.page.config;

import com.supergo.page.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
                .excludePathPatterns("/**/*.html","/**/*.css","/**/*.js","/**/*.png","/**/*.jpg","/**/*.json","/**/*.min.map")
                .excludePathPatterns("/**/*.eto","/**/*.svg","/**/*.ttf","/**/*.woff")
                .excludePathPatterns("/error","/undefined")
                .excludePathPatterns("/**/provinces/**");  //对含有provinces接口放行
                //.excludePathPatterns("/**/html/build/**");  //对html页面生成方法放行

    }

    /**
     * 跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH","OPTIONS")
                .maxAge(3600 * 24);
    }

}
