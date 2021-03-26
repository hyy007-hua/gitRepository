package com.chzu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    //默认生成的api文档地址是 http://localhost:8088/swagger-ui.html
    //另一个地址可以为 http://localhost:8088/doc.html
    //配置swagger核心配置 docket
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)      //指定api类型为 swagger2
                    .apiInfo(apiInfo())                     //定义api文档汇总信息
                    .select()
                    .apis(RequestHandlerSelectors
                            .basePackage("com.chzu.controller"))//指定controller包
                    .paths(PathSelectors.any())              //所有controller
                    .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("毕业设计 学年设计管理系统api文档")           //文档标题
                .contact(new Contact("hyy",
                        "http://www.test.com",
                        "1693018918@qq.com"))        //联系人信息
                .description("这是我的毕业设计，学年设计管理系统相关的api信息")      //文档描述
                .version("1.0.0")                           //版本号
                .termsOfServiceUrl("http://www.test.com")   //网站地址
                .build();
    }

}
