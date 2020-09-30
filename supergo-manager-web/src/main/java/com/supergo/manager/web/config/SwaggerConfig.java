package com.supergo.manager.web.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * 文档生成配置
 */
@Configuration
@EnableSwagger2   //开启swagger
public class SwaggerConfig {

    @Bean
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)    //当前文档的格式
                .apiInfo(groupApiInfo())    //配置文档里显示的一些信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.supergo.manager.web.controller"))  //基于哪个包来扫描
                .paths(PathSelectors.any())   //路径
                .build();
// .securityContexts(Lists.newArrayList(securityContext(),securityContext1()))    //认证信息
// .securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey(),apiKey1()));
    }
    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui很棒~~~！！！")   //文档标题
                .description("<div style='font-size:14px;color:red;'>swaggerbootstrap-ui-demo RESTful APIs</div>") //项目介绍
                .termsOfServiceUrl("http://www.xx.com/")  //团队介绍
//.contact("group@qq.com")
                .version("1.0")
                .build();
    }
    private ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }
    private ApiKey apiKey1() {
        return new ApiKey("BearerToken1", "Authorization-x", "header");
    }
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }
    private SecurityContext securityContext1() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth1())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global",
                "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("BearerToken",
                authorizationScopes));
    }
    List<SecurityReference> defaultAuth1() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global",
                "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("BearerToken1",
                authorizationScopes));
    }

}
