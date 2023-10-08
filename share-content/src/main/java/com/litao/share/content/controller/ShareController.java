package com.litao.share.content.controller;

import com.litao.share.common.resp.CommonResp;
import com.litao.share.content.domain.entity.Notice;
import com.litao.share.content.service.NoticeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/share")
public class ShareController {

    @Resource
    private NoticeService noticeService;

    @GetMapping(value = "/notice")
    public CommonResp<Notice> getLatesNotice(){
        CommonResp<Notice> commonResp=new CommonResp<>();
        commonResp.setData(noticeService.getLatest());
        return commonResp;
    }
}
