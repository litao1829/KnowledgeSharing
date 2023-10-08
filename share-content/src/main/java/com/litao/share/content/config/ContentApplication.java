package com.litao.share.content.config;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan("com.litao")
@Slf4j
@MapperScan("com.litao.share.*.mapper")
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ContentApplication.class);
        Environment env = springApplication.run(args).getEnvironment();
        log.info("启动成功");

    }
}