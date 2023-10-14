package com.litao.share.content.controller;

import com.litao.share.common.resp.CommonResp;
import com.litao.share.content.domain.dto.ShareAuditDTO;
import com.litao.share.content.domain.entity.Share;
import com.litao.share.content.service.ShareService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/share/admin")
public class ShareAdminController {
    @Resource
    private ShareService shareService;


    /**
     * 审核列表
     * @return
     */
    @GetMapping("/list")
    public CommonResp<List<Share>> getShareNotYet(){
        CommonResp<List<Share>> commonResp=new CommonResp<>();
        commonResp.setData(shareService.querySharesNotYet());
        return commonResp;
    }

    /**
     * 管理审核资源接口
     * @param id
     * @param auditDTO
     * @return
     */
    @PostMapping(value = "/audit/{id}")
    public CommonResp<Share> auditById(@PathVariable("id")Long id, @RequestBody ShareAuditDTO auditDTO){
        CommonResp<Share> commonResp=new CommonResp<>();
        commonResp.setData(shareService.auditById(id,auditDTO));
        return commonResp;
    }
}
