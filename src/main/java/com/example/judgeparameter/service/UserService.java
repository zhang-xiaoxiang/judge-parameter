package com.example.judgeparameter.service;

import com.example.judgeparameter.entity.User;

/**
 * UserService:
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/22
 */
public interface UserService {
    /**
     * 新增用户
     * @param user
     * @return
     */
    Integer addUser(User user);
}
