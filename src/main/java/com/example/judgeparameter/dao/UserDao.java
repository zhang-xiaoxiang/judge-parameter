package com.example.judgeparameter.dao;

import com.example.judgeparameter.entity.User;

/**
 * UserDao:
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/22
 */
public interface UserDao {
    /**
     * 增加一用户
     * @param user
     * @return
     */
    Integer addUser(User user);
}
