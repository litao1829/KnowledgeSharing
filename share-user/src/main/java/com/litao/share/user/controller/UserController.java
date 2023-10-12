package com.litao.share.user.controller;

import com.litao.share.common.resp.CommonResp;
import com.litao.share.user.domain.dto.LoginDTO;
import com.litao.share.user.domain.dto.UserAddBonusMsgDTO;
import com.litao.share.user.domain.entity.User;
import com.litao.share.user.domain.resp.UserLoginResp;
import com.litao.share.user.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/count")
    public CommonResp<Long> count(){
        Long count = userService.count();
        CommonResp<Long> commonResp=new CommonResp<>();
        commonResp.setData(count);
        return commonResp;
    }

    @PostMapping("/login")
    public CommonResp<UserLoginResp> login(@Valid @RequestBody LoginDTO loginDTO){
        UserLoginResp userLoginResp = userService.login(loginDTO);
        CommonResp<UserLoginResp> commonResp=new CommonResp<>();
        commonResp.setData(userLoginResp);
        return commonResp;
    }

    @PostMapping("/register")
    public CommonResp<Long> register( @Valid @RequestBody LoginDTO loginDTO){
        Long id = userService.register(loginDTO);
        CommonResp<Long> commonResp=new CommonResp<>();
        commonResp.setData(id);
        return  commonResp;
    }

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResp<User> getUserById(@PathVariable("id")Long id){
        User user=userService.findById(id);
        CommonResp<User> commonResp=new CommonResp<>();
        commonResp.setData(user);
        return commonResp;
    }

    /**
     * 用户兑换修改积分
     * @param userAddBonusMsgDTO
     * @return
     */
    @PutMapping(value = "/update-bouns")
    public CommonResp<User> updateBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO){
        Long userId = userAddBonusMsgDTO.getUserId();
        userService.updateBonus(
                UserAddBonusMsgDTO.builder()
                        .userId(userId)
                        .bonus(userAddBonusMsgDTO.getBonus())
                        .description("兑换分享")
                        .event("BUY")
                        .build());
        CommonResp<User> commonResp=new CommonResp<>();
        User user = userService.findById(userId);
        commonResp.setData(user);
        return commonResp;
    }
}
