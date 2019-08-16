package com.example.judgeparameter.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * User:User实体类
 *
 * @author zhangxiaoxiang
 * @date 2019/8/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户主键ID
     */
    private String userId;


    /**
     * 用户昵称---
     */
    private String userName;

    /**
     * 用户手机号---
     */

    private String userPhone;

    /**
     * 用户登录密码---
     */
    private String userPassword;

    /**
     * 用户邮箱地址---
     */
    private String userEmail;


}