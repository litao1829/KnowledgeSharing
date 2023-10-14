package com.litao.share.content.domain.dto;

import com.litao.share.content.enums.AuditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareAuditDTO {
    /**
     * 审核状态：枚举
     */
    private AuditStatusEnum auditStatusEnum;

    /**
     * 原因
     */
    private String reason;

    /**
     * 是否发布显示
     */
    private Boolean showFlag;
}
