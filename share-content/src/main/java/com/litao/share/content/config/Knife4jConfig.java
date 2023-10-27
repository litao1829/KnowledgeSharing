package com.litao.share.content.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {
    @Bean
    public GroupedOpenApi userApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"com.litao.share.content.controller"};
        return GroupedOpenApi.builder().group("内容中心")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public OpenAPI customOpenApi() {
        Contact contact = new Contact();
        contact.setName("");

        return new OpenAPI().info(new Info()
                .title("内容中心接口文档")
                .description("内容中心接口文档")
                .contact(contact)
                .version("1.0.0")
                .termsOfService(""));
    }
}
