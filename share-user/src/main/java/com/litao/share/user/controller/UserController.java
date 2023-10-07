package com.litao.share.user.controller;

import com.litao.share.user.domain.dto.LoginDTO;
import com.litao.share.user.domain.entity.User;
import com.litao.share.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/count")
    public Long count(){
        return userService.count();
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }
}
