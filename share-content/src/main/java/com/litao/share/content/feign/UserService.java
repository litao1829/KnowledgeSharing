package com.litao.share.content.feign;

import com.litao.share.common.resp.CommonResp;
import com.litao.share.content.domain.dto.UserAddBonusMsgDTO;
import com.litao.share.content.domain.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-service",path = "/user")
public interface UserService {

    /**
     * 调用用户中心根据id查询用户信息接口
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    CommonResp<User> getUser(@PathVariable("id") Long id);


    /**
     * 调用用户中心修改用户接口
     * @param userAddBonusMsgDTO
     * @return
     */
    @PutMapping(value = "/update-bouns")
    CommonResp<User> updateBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO);
}
