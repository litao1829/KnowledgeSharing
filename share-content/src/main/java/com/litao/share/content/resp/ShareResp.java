package com.litao.share.content.resp;

import com.litao.share.content.domain.entity.Share;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareResp {

    /**
     * 分享对象
     */
    private Share share;

    /**
     * 发布者昵称
     */
    private String nickname;

    /**
     * 发布者头像
     */
    private String avatarUrl;
}
