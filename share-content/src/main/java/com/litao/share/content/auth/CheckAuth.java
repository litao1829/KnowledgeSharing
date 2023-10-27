package com.litao.share.content.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 实现鉴权
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuth {

    String value();
}
