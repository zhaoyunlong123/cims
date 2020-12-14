package com.javakc.cims.util.cache;

import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

public class MybatisCache implements Cache {

    //哪个模块
    @Override
    public String getId() {
        return null;
    }

    //缓存写入方法
    @Override
    public void putObject(Object key, Object value) {

    }

    //缓存读取方法
    @Override
    public Object getObject(Object key) {
        return null;
    }

    //移除缓存方法
    @Override
    public Object removeObject(Object key) {
        return null;
    }

    //全部清空
    @Override
    public void clear() {

    }

    //缓存长度
    @Override
    public int getSize() {
        return 0;
    }

    //读写锁
    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
