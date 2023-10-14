package com.litao.share.content.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     * 待审核
     */
    NOT_KEY,
    /**
     * 审核通过
     */
    PASS,
    /**
     * 审核通过
     */
    REJECT
}
