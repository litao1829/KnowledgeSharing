package com.litao.share.content.controller;

import com.litao.share.common.resp.CommonResp;
import com.litao.share.content.domain.entity.Share;
import com.litao.share.content.service.ShareService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/share/admin")
public class ShareAdminController {
    @Resource
    private ShareService shareService;


    @GetMapping("/list")
    public CommonResp<List<Share>> getShareNotYet(){
        CommonResp<List<Share>> commonResp=new CommonResp<>();
        commonResp.setData(shareService.querySharesNotYet());
        return commonResp;
    }
}
