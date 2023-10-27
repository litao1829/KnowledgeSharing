package com.litao.share.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.litao.share.common.exception.BusinessException;
import com.litao.share.common.exception.BusinessExceptionEnum;
import com.litao.share.common.util.JwtUtil;
import com.litao.share.common.util.SnowUtil;
import com.litao.share.user.domain.dto.LoginDTO;
import com.litao.share.user.domain.dto.UserAddBonusMsgDTO;
import com.litao.share.user.domain.entity.BonusEventLog;
import com.litao.share.user.domain.entity.User;
import com.litao.share.user.domain.resp.UserLoginResp;
import com.litao.share.user.mapper.BonusEventLogMapper;
import com.litao.share.user.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private BonusEventLogMapper bonusEventLogMapper;


    /**
     * 修改用户积分，同时添加明细信息
     * @param userAddBonusMsgDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateBonus(UserAddBonusMsgDTO userAddBonusMsgDTO){
        log.info(userAddBonusMsgDTO.toString());

        //1.为用户修改积分
        Long userId=userAddBonusMsgDTO.getUserId();
        Integer bonus = userAddBonusMsgDTO.getBonus();
        User user = userMapper.selectById(userId);
        user.setBonus(user.getBonus()+bonus);
        userMapper.update(user,new QueryWrapper<User>().lambda().eq(User::getId,userId));


        //2.记录日志到bonus_event_log表里
        bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bonus)
                        .description(userAddBonusMsgDTO.getDescription())
                        .event(userAddBonusMsgDTO.getEvent())
                        .createTime(new Date())
                        .build()
        );

        log.info("积分添加完毕....");
    }


    /**
     * 统计用户数量
     * @return
     */
    public Long count(){
        return userMapper.selectCount(null);
    }

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    public UserLoginResp login(LoginDTO loginDTO){
        //根据用户手机查询用户
        User userDB = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, loginDTO.getPhone()));

        //没有找到，抛出运行时异常
        if(userDB==null){
            throw new BusinessException(BusinessExceptionEnum.PHONE_NOT_EXIST);
        }

        //密码错误
        if(!userDB.getPassword().equals(loginDTO.getPassword())){
            throw new BusinessException(BusinessExceptionEnum.PASSWORD_ERROR);
        }

        //都正确，返回

        UserLoginResp userLoginResp= UserLoginResp.builder()
                .user(userDB)
                .build();

//        String key="litao";
//        Map<String,Object> map= BeanUtil.beanToMap(userLoginResp);
//        String token = JWTUtil.createToken(map, key.getBytes());
        String token = JwtUtil.createToken(userLoginResp.getUser().getId(),
                                userLoginResp.getUser().getPhone());
        userLoginResp.setToken(token);
        return userLoginResp;
    }

    /**
     * 用户注册
     * @param loginDTO
     * @return
     */
    public Long register(LoginDTO loginDTO){
        //根据用户手机查询用户
        User userDB = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, loginDTO.getPhone()));

        //找到了，手机号已被注册
        if(userDB!=null){
            throw new BusinessException(BusinessExceptionEnum.PHONE_EXIST);
        }

        User saveUser= User.builder()
                //使用雪花算法生成id
                .id(SnowUtil.getSnowflakeNextId())
                .phone(loginDTO.getPhone())
                .password(loginDTO.getPassword())
                .nickname("新用户")
                .roles("user")
                .avatarUrl("https://i2.100024.xyz/2023/01/26/3e727b.webp")
                .bonus(100)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        userMapper.insert(saveUser);
        return saveUser.getId();
    }


    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    public User findById(Long userId){
        return userMapper.selectById(userId);
    }


    /**
     * 根据用户查id询明细
     * @param userId
     * @return
     */
    public List<BonusEventLog> LogByUserId(Long userId){
        LambdaQueryWrapper<BonusEventLog> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByDesc(BonusEventLog::getCreateTime);
        wrapper.eq(BonusEventLog::getUserId,userId);
        List<BonusEventLog> logs = bonusEventLogMapper.selectList(wrapper);
        return logs;
    }
}
