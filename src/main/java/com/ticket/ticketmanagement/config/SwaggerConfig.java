package com.ticket.ticketmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author VernHe
 * @date 2021年05月24日 14:23
 *
 * Swagger配置类，配置界面以及所关联的接口
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket creatRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ticket.ticketmanagement.controller")) //swagger将的搜索的包
                .paths(PathSelectors.any()) //swagger的路径匹配规则
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot-车票管理系统接口文档")
                .description("接口文档")
                .version("version 1.0")
                .build();
    }
}
