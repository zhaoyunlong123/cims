package com.javakc.cims.person.factory;

import com.javakc.cims.person.dao.PersonDao;
import com.javakc.cims.person.dao.impl.PersonDaoImpl;
import com.javakc.cims.person.service.PersonService;
import com.javakc.cims.person.service.impl.PersonServiceImpl;

/**
 * [干部收入管理系统]
 * [人员管理-人员工厂实现类]
 */
public class PersonFactory {

    /**
     * 获取人员模块逻辑层实现
     * @return 人员逻辑层实现
     */
    public static PersonService getPersonService()
    {
        return new PersonServiceImpl();
    }

    /**
     * 获取人员模块数据层实现
     * @return 人员数据层实现
     */
    public static PersonDao getPersonDao()
    {
        return PersonDaoImpl.getInstance();
    }
}
