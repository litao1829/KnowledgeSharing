package com.litao.share.content.controller;

import com.litao.share.common.resp.CommonResp;
import com.litao.share.content.auth.CheckAuth;
import com.litao.share.content.domain.dto.ShareAuditDTO;
import com.litao.share.content.domain.entity.Share;
import com.litao.share.content.service.ShareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/share/admin")
@Slf4j
@Tag(name = "管理员接口")
public class ShareAdminController {
    @Resource
    private ShareService shareService;


    /**
     * 审核列表
     * @return
     */
    @CheckAuth("admin")
    @Operation(summary = "审核列表")
    @GetMapping("/list")
    public CommonResp<List<Share>> getShareNotYet(){
        CommonResp<List<Share>> commonResp=new CommonResp<>();
        List<Share> shares = shareService.querySharesNotYet();
        log.info(shares.toString());
        commonResp.setData(shares);
        return commonResp;
    }

    /**
     * 管理审核资源接口
     * @param id
     * @param auditDTO
     * @return
     */
    @CheckAuth("admin")
    @Operation(summary = "审核资源接口")
    @PostMapping(value = "/audit/{id}")
    public CommonResp<Share> auditById(@PathVariable("id")Long id, @RequestBody ShareAuditDTO auditDTO){
        CommonResp<Share> commonResp=new CommonResp<>();
        commonResp.setData(shareService.auditById(id,auditDTO));
        return commonResp;
    }
}
