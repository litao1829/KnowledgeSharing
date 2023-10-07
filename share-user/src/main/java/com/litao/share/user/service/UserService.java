package com.litao.share.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.litao.share.user.domain.dto.LoginDTO;
import com.litao.share.user.domain.entity.User;
import com.litao.share.user.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

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
    public User login(LoginDTO loginDTO){
        //根据用户手机查询用户
        User userDB = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, loginDTO.getPhone()));

        //没有找到，抛出运行时异常
        if(userDB==null){
            throw new RuntimeException("手机号不存在");
        }

        //密码错误
        if(!userDB.getPassword().equals(loginDTO.getPassword())){
            throw new RuntimeException("密码错误");
        }

        //都正确，返回
        return userDB;
    }

}
