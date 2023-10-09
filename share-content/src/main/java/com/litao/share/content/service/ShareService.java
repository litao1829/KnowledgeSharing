package com.litao.share.content.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.util.StringUtil;
import com.litao.share.content.domain.entity.MidUserShare;
import com.litao.share.content.domain.entity.Share;
import com.litao.share.content.mapper.MidUserShareMapper;
import com.litao.share.content.mapper.ShareMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShareService {
    @Resource
    private ShareMapper shareMapper;
    @Resource
    private MidUserShareMapper midUserShareMapper;


    /**
     * 查询某个用户首页可见的资源列表
     * @param title
     * @param userId
     * @return
     */
    public List<Share> getList(String title,Integer pageNo,Integer pageSize,Long userId){
        //构造查询条件
        LambdaQueryWrapper<Share> wrapper=new LambdaQueryWrapper<>();
        //按照id降序查询所有数据
        wrapper.orderByDesc(Share::getId);
        //如果标题关键字不空，则加上模糊查询条件，否则结果及所有数据
        if(StringUtil.isNotEmpty(title)){
            wrapper.like(Share::getTitle,title);
        }

        //过滤出所有已经通过审核的数据并需要显示的数据
        wrapper.eq(Share::getAuditStatus,"PASS").eq(Share::getShowFlag,true);
        Page<Share> page=Page.of(pageNo,pageSize);
        //执行按条件查询
        List<Share> shares = shareMapper.selectList(page,wrapper);


        //处理后的Share数据列表
        List<Share> shareDeal;
        //如果用户没有登录，那么downloadUrl全部为null
        if(userId==null){
            shareDeal=shares.stream().peek(share->share.setDownloadUrl(null)).collect(Collectors.toList());
        }

        //2.如果用户登录了，那么查询到mid-user-share，如果没有数据，那么这条share的downloadUrl也设为null
        else {
            shareDeal=shares.stream().peek(share->{
                MidUserShare midUserShare = midUserShareMapper.selectOne(new QueryWrapper<MidUserShare>().lambda()
                        .eq(MidUserShare::getShareId, share.getId()).
                        eq(MidUserShare::getUserId, userId));
                if(midUserShare==null){
                    share.setDownloadUrl(null);
                }
            }).collect(Collectors.toList());

        }
        return shareDeal;
    }
}
