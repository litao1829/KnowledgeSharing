package com.litao.share.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试接口")
@RestController
public class TestController {

    @Operation(summary = "测试")
    @GetMapping("/hello")
    public String hello(){
        int a=1/0;
        return "hello world";
    }
}
