package com.litao.share.content.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Share {

    private Long id;

    private Long userId;

    private String title;

    private Boolean isOriginal;

    private String author;

    private String cover;

    private String summary;

    private Integer price;

    private String downloadUrl;

    private Integer buyCount;

    private Boolean showFlag;

    private String auditStatus;

    private String reason;


    @JsonFormat(locale = "zh",timezone = "GTM+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Data createTime;

    @JsonFormat(locale = "zh",timezone = "GTM+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Data updateTime;
}
