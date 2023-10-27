package com.litao.share.user.controller;

import com.litao.share.common.resp.CommonResp;
import com.litao.share.user.domain.dto.LoginDTO;
import com.litao.share.user.domain.dto.UserAddBonusMsgDTO;
import com.litao.share.user.domain.entity.BonusEventLog;
import com.litao.share.user.domain.entity.User;
import com.litao.share.user.domain.resp.UserLoginResp;
import com.litao.share.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Tag(name = "用户接口")
public class UserController {
    @Resource
    private UserService userService;

    @Value("${web.custom-file-upload}")
    private String uploadPath;


    @Operation(summary = "返回用户数量")
    @GetMapping("/count")
    public CommonResp<Long> count(){
        Long count = userService.count();
        CommonResp<Long> commonResp=new CommonResp<>();
        commonResp.setData(count);
        return commonResp;
    }

    @Operation(summary = "登录接口")
    @PostMapping("/login")
    public CommonResp<UserLoginResp> login(@Valid @RequestBody LoginDTO loginDTO){
        UserLoginResp userLoginResp = userService.login(loginDTO);
        CommonResp<UserLoginResp> commonResp=new CommonResp<>();
        commonResp.setData(userLoginResp);
        return commonResp;
    }

    @Operation(summary = "注册接口")
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
    @Operation(summary = "根据ID查询用户")
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
    @Operation(summary = "修改用户积分")
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

    /**
     * 根据用户id查询明细
     * @param id
     * @return
     */
    @Operation(summary = "返回积分明细")
    @GetMapping("/logs/{id}")
    public CommonResp<List<BonusEventLog>> getLogsByUserId(@PathVariable("id")Long id){
        CommonResp<List<BonusEventLog>> commonResp=new CommonResp<>();
        List<BonusEventLog> logs = userService.LogByUserId(id);
        commonResp.setData(logs);
        return commonResp;
    }


    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public CommonResp<String> upload(@RequestParam("file")MultipartFile file, HttpServletRequest request)throws Exception {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File fileDir = new File(uploadPath + date);

        if (!fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        String originFileName=file.getOriginalFilename();
        if(StringUtils.isBlank(originFileName)){
            return CommonResp.error();
        }
        String suffix=originFileName.substring(originFileName.lastIndexOf("."));
        String newFileName= UUID.randomUUID()+suffix;

        //上传：文件复制搬运
        file.transferTo(new File(fileDir,newFileName));

        //拼接返回上传文件访问路径
        return CommonResp.success(String.format("%s://%s:%s/%s/%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                date,newFileName));
    }
}
