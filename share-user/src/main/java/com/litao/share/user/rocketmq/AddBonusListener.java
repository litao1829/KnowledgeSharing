package com.litao.share.user.rocketmq;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.litao.share.user.domain.dto.UserAddBounsMQDTO;
import com.litao.share.user.domain.entity.BonusEventLog;
import com.litao.share.user.domain.entity.User;
import com.litao.share.user.mapper.BonusEventLogMapper;
import com.litao.share.user.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RocketMQMessageListener(consumerGroup = "test-group",topic="add-bouns")
@Slf4j
public class AddBonusListener implements RocketMQListener<UserAddBounsMQDTO> {


    @Resource
    private UserMapper userMapper;

    @Resource
    private BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void onMessage(UserAddBounsMQDTO userAddBounsMQDTO) {
        log.info(String.valueOf(userAddBounsMQDTO));
        //1.为用户加积分
        Long userId = userAddBounsMQDTO.getUserId();
        User user = userMapper.selectById(userId);
        user.setBonus(user.getBonus()+userAddBounsMQDTO.getBonus());
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getId,userId);
        userMapper.update(user,wrapper);

        //2.新增积分日志
        bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(userAddBounsMQDTO.getBonus())
                        .event("CONTRIBUTE")
                        .createTime(new Date())
                        .description("投稿加积分")
                        .build()
        );

    }
}
