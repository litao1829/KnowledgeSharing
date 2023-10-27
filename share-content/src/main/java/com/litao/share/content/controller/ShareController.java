package com.litao.share.content.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWTUtil;
import com.litao.share.common.resp.CommonResp;
import com.litao.share.common.util.JwtUtil;
import com.litao.share.content.domain.dto.ExchangeDTO;
import com.litao.share.content.domain.dto.ShareRequestDTO;
import com.litao.share.content.domain.entity.Notice;
import com.litao.share.content.domain.entity.Share;
import com.litao.share.content.resp.ShareResp;
import com.litao.share.content.service.NoticeService;
import com.litao.share.content.service.ShareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/share")
@Slf4j
@RefreshScope
@Tag(name = "普通用户接口")
public class ShareController {
    @Resource
    private ShareService shareService;

    @Resource
    private NoticeService noticeService;


    @Value("${open}")
    private Boolean open;

    private final int MAX=100;

    @Operation(summary = "消息接口")
    @GetMapping(value = "/notice")
    public CommonResp<Notice> getLatesNotice(){
        CommonResp<Notice> commonResp=new CommonResp<>();
        if(!open){
            commonResp.setData(Notice.builder()
                    .content("系统正在维护中...")
                    .showFlag(true)
                    .id(0L)
                    .build());

            return commonResp;
        }
        commonResp.setData(noticeService.getLatest());
        return commonResp;
    }



    /**
     * 
     * @param title
     * @return
     */
    @Operation(summary = "知识分享列表接口")
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
        if(token==null){
            return null;
        }
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


    /**
     * 根据shareId返回share和发布人信息
     * @param id
     * @return
     */
    @Operation(summary = "根据ID查询分享接口")
    @GetMapping("/{id}")
    public CommonResp<ShareResp> getShareById(@PathVariable("id") Long id){
        ShareResp shareResp = shareService.findById(id);
        CommonResp<ShareResp> commonResp=new CommonResp<>();
        commonResp.setData(shareResp);
        return commonResp;
    }

    /**
     * 兑换接口
     * @param exchangeDTO
     * @return
     */
    @Operation(summary = "兑换接口")
    @PostMapping("/exchange")
    public CommonResp<Share> exchange(@RequestBody ExchangeDTO exchangeDTO){
        CommonResp<Share> commonResp =new CommonResp<>();
        commonResp.setData(shareService.exchange(exchangeDTO));
        return commonResp;
    }


    /**
     * 投稿接口
     * @param shareRequestDTO
     * @param token
     * @return
     */
    @Operation(summary = "投稿接口")
    @PostMapping("/contribute")
    public CommonResp<Integer> contributeShare(@RequestBody ShareRequestDTO shareRequestDTO,
                               @RequestHeader(value = "token",required = false)String token){
        Long userId = getUserIdFromToken(token);
        shareRequestDTO.setUserId(userId);
        log.info(shareRequestDTO.toString());
        int contribute = shareService.contribute(shareRequestDTO);
        CommonResp<Integer> commonResp=new CommonResp<>();
        commonResp.setData(contribute);
        return commonResp;
    }

    /**
     * 我的投稿
     * @param pageNo
     * @param pageSize
     * @param token
     * @return
     */
    @Operation(summary = "我的投稿查询接口")
    @GetMapping("/my-contribute")
    public CommonResp<List<Share>> myContribute(
            @RequestParam(required = false,defaultValue = "1")Integer pageNo,
            @RequestParam(required = false,defaultValue = "8")Integer pageSize,
            @RequestHeader(required = false,value = "token")String token){
        if(pageSize>MAX){
            pageSize=MAX;
        }

        Long userId = getUserIdFromToken(token);
        List<Share> shares = shareService.myContribute(pageNo, pageSize, userId);
        CommonResp<List<Share>> commonResp=new CommonResp<>();
        commonResp.setData(shares);
        return commonResp;
    }

    /**
     * 查看我的兑换
     * @param token
     * @return
     */
    @Operation(summary = "我的兑换接口")
    @GetMapping("/myexchange")
    public CommonResp<List<Share>> exchangeListByUserId(@RequestHeader(required = false,value = "token")String token){
        Long userId = getUserIdFromToken(token);
        List<Share> shares = shareService.exchangeListByUserId(userId);
        CommonResp<List<Share>> commonResp=new CommonResp<>();
        commonResp.setData(shares);
        return commonResp;
    }
}
