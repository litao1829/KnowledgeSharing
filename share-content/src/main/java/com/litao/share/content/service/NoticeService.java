package com.litao.share.content.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.litao.share.content.domain.entity.Notice;
import com.litao.share.content.mapper.NoticeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    public Notice getLatest(){
        LambdaQueryWrapper<Notice> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Notice::getShowFlag,1);
        wrapper.orderByDesc(Notice::getId);
        List<Notice> notices = noticeMapper.selectList(wrapper);
        return notices.get(0);
    }
}
