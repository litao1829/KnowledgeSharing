package com.litao.share.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.litao.share.content.domain.entity.Share;

import java.util.List;

public interface ShareMapper extends BaseMapper<Share> {


    public List<Share> selectExchangeByUserId(Long userId);
}
