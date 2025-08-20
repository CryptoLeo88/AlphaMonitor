package com.block.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                // 匹配所有控制器
                .apis(RequestHandlerSelectors.basePackage("com.block.controller"))
                // 匹配所有路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        // 访问地址：http://localhost:8080/swagger-ui/index.html
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("描述")
                .termsOfServiceUrl("服务地址")
                .contact(new Contact("作者名称", "作者个人地址", "作者邮箱"))
                .license("协议")
                .licenseUrl("协议链接地址")
                .version("版本")
                .build();
    }
}
