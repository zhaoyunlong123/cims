package com.javakc.cims.user.factory;

import com.javakc.cims.user.dao.UserDao;
import com.javakc.cims.user.dao.impl.UserDaoImpl;
import com.javakc.cims.user.service.UserService;
import com.javakc.cims.user.service.impl.UserServiceImpl;

public class UserFactory {

    /**
     * 获取用户逻辑层实现类
     * @return 用户逻辑层实现类
     */
    public static UserService getUserService()
    {
        return new UserServiceImpl();
    }

    /**
     * 获取用户数据层实现类
     * @return 用户数据层实现类
     */
    public static UserDao getUserDao()
    {
        return UserDaoImpl.getInstance();
    }

}
