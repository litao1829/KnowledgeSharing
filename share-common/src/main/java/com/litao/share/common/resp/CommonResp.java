package com.litao.share.common.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResp<T>{
    /**
     * 业务成功或失败
     */
    private Boolean success=true;

    /**
     * 返回信息
     */
    private String message="成功";

    /**
     * 返回泛型数据，自定义类型
     */
    private T data;

    public static <E> CommonResp<E> success(E data){
        CommonResp<E> commonResp=new CommonResp<>();
        commonResp.setData(data);
        commonResp.setSuccess(true);
        commonResp.setMessage("成功");
        return  commonResp;
    }

    public static CommonResp error(){
        return new CommonResp(false,"服务端异常",null);
    }
}
