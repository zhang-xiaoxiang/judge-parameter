package com.example.judgeparameter.serviceimpl;

import com.example.judgeparameter.dao.UserDao;
import com.example.judgeparameter.entity.User;
import com.example.judgeparameter.exception.MyException;
import com.example.judgeparameter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl:用户实现类
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/22
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 这里没有使用XML实现,示意一下就行了
     */
    // @Autowired
    // private UserDao userDao;

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        //一大堆判断user的属性是否为空
        if (StringUtils.isBlank(user.getUserName())) {
            throw new MyException("用户名不能为空!");
        }
        if (StringUtils.isBlank(user.getUserPassword())) {
            throw new MyException("密码不能为空!");
        }
        if (StringUtils.isBlank(user.getUserEmail())) {
            throw new MyException("邮箱不能为空!");
        }
        if (StringUtils.isBlank(user.getUserPhone())){
            throw new MyException("电话号码不能为空!");
        }
        //..........此处省略一大堆非空判断..........

        //下面链接数据库,没有实现,知道大概流程就行了
        //userDao.addUser(user);
        //如果参数均正常不为空,那么模拟返回数据库这个  1
        return 1;
    }
}
