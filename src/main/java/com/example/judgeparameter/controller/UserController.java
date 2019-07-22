package com.example.judgeparameter.controller;

import com.example.judgeparameter.aop.RequestRequire;
import com.example.judgeparameter.entity.User;
import com.example.judgeparameter.result.ResponseData;
import com.example.judgeparameter.result.ResponseDataUtil;
import com.example.judgeparameter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController:用户控制层
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/22
 */
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 使用json格式传输参数,判断参数是否为空
     *
     * @param user
     * @return
     */
    @RequestMapping("/add-user")
    @RequestRequire(require = "userName,userPhone,userPassword,userEmail",parameter = User.class)
    public ResponseData addUser(@RequestBody User user) {
        Integer integer = userService.addUser(user);
        if (1 == integer) {
            return ResponseDataUtil.success("添加用户成功!");
        } else {
            return ResponseDataUtil.failure("添加用户失败!");
        }

    }
}
