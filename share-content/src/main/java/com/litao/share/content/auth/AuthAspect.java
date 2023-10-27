package com.litao.share.content.auth;

import cn.hutool.json.JSONObject;
import com.litao.share.common.util.JwtUtil;
import com.litao.share.content.domain.entity.User;
import com.litao.share.content.feign.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class AuthAspect {
    @Resource
    private UserService userService;

    @Around("@annotation(com.litao.share.content.auth.CheckAuth)")
    public Object checkAuth(ProceedingJoinPoint point)throws Throwable{
        try {
            //1.验证token是否合法
            HttpServletRequest request=getHttpServletRequest();
            String token=request.getHeader("token");
            boolean validate = JwtUtil.validate(token);
            if(!validate){
                throw  new RuntimeException("Token 不合法！");
            }

            //2.验证用户角色是否匹配
            JSONObject jsonObject = JwtUtil.getJSONObject(token);
            long userId=Long.parseLong(jsonObject.get("id").toString());
            User user=userService.getUser(userId).getData();
            String roles=user.getRoles();
            log.info("当前用户角色：------------"+roles);
            MethodSignature signature=(MethodSignature)point.getSignature();
            Method method = signature.getMethod();
            CheckAuth auth = method.getAnnotation(CheckAuth.class);
            String value = auth.value();
            if(!Objects.equals(roles,value)){
                throw new RuntimeException("用户无权访问！");
            }
        }
        catch (Throwable throwable){
            throw new RuntimeException("用户无权访问！",throwable);
        }
        return point.proceed();
    }


    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        assert attributes !=null;
        return attributes.getRequest();
    }
}
