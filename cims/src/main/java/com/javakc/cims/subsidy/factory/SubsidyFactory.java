package com.javakc.cims.subsidy.factory;


import com.javakc.cims.subsidy.dao.SubsidyDao;
import com.javakc.cims.subsidy.dao.impl.SubsidyDaoImpl;
import com.javakc.cims.subsidy.service.SubsidyService;
import com.javakc.cims.subsidy.service.impl.SubsidyServiceImpl;


public class SubsidyFactory {
    /**
     * 获取补贴、供暖模块逻辑层实现
     * @return 补贴、供暖逻辑层实现
     */
    public static SubsidyService getSubsidyService()
    {
        return new SubsidyServiceImpl();
    }

    /**
     * 获取补贴、供暖模块数据层实现
     * @return 补贴、供暖数据层实现
     */
    public static SubsidyDao getSubsidyDao()
    {
        return SubsidyDaoImpl.getInstance();
    }
}
