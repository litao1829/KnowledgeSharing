package com.litao.share.content.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWTUtil;
import com.litao.share.common.resp.CommonResp;
import com.litao.share.common.util.JwtUtil;
import com.litao.share.content.domain.entity.Notice;
import com.litao.share.content.domain.entity.Share;
import com.litao.share.content.service.NoticeService;
import com.litao.share.content.service.ShareService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/share")
@Slf4j
public class ShareController {
    @Resource
    private ShareService shareService;

    @Resource
    private NoticeService noticeService;

    private final int MAX=100;

    @GetMapping(value = "/notice")
    public CommonResp<Notice> getLatesNotice(){
        CommonResp<Notice> commonResp=new CommonResp<>();
        commonResp.setData(noticeService.getLatest());
        return commonResp;
    }

    /**
     * 
     * @param title
     * @return
     */
    @GetMapping("/list")
    public CommonResp<List<Share>> getShareList(@RequestParam(required = false)String title,
                                                @RequestParam(required = false,defaultValue = "1")Integer pageNo,
                                                @RequestParam(required = false,defaultValue = "3")Integer pageSize,
                                                @RequestHeader(value = "token",required = false)String token){
        CommonResp<List<Share>> commonResp=new CommonResp<>();
        if(pageSize>MAX){
            pageSize=MAX;
        }
        Long userId=getUserIdFromToken(token);
        commonResp.setData(shareService.getList(title,pageNo,pageSize,userId));
        return commonResp;
    }


    /**
     * 封装一个token中提取userId的方法
     * @param token
     * @return
     */
    private Long getUserIdFromToken(String token) {
        log.info(">>>>>>>>>>>>>>token："+token);
        long userId=0;
        String noToken="no-token";
        if(!noToken.equals(token)){
            JSONObject jsonObject = JwtUtil.getJSONObject(token);
            log.info("解析到token的json数据为：{}",jsonObject);
            userId=Long.parseLong(jsonObject.get("id").toString());
        }else {
            log.info("没有 token");
        }
        return userId;
    }

}
