package com.javakc.cims.subsidy.dao.impl;

import com.javakc.cims.subsidy.dao.SubsidyDao;
import com.javakc.cims.subsidy.entity.Subsidy;
import com.javakc.cims.util.mybatis.SqlSessionUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * [离退休补贴管理系统]
 * [退休补贴-补贴数据层实现类]
 */
public class SubsidyDaoImpl implements SubsidyDao {

    private SubsidyDaoImpl(){

    }
    private static class SubsidyDaoImplHolder{
        private static SubsidyDaoImpl instance = new SubsidyDaoImpl();
    }

    public static SubsidyDaoImpl getInstance(){return SubsidyDaoImplHolder.instance;}

    /**
     * 离退休人员补贴添加
     * @param subsidy 对象
     * @return 状态-成功返回1，失败返回0
     */
    @Override
    public int insert(Subsidy subsidy) {
        SqlSession session = SqlSessionUtils.openSession(true);
        int count = session.insert("subsidy.insert",subsidy);
        session.close();
        return count;
    }
    /**
     * 离退休人员补贴信息修改
     * @param entity 对象
     * @return 状态-成功返回1，失败返回0
     */
    @Override
    public int update(Subsidy entity) {
        SqlSession session = SqlSessionUtils.openSession(true);
        int count = session.update("subsidy.update",entity);
        session.close();
        return count;
    }
    /**
     * 离退休人员补贴删除
     * @param id 对象
     * @return 状态-成功返回1，失败返回0
     */
    @Override
    public int delete(int id) {
        SqlSession session = SqlSessionUtils.openSession(true);
        int count = session.delete("subsidy.delete",id);
        session.close();
        return count;
    }
    /**
     * 离退休人员补贴删除
     * @param ids 对象
     * @return 状态-成功返回1，失败返回0
     */
    @Override
    public int delete(String... ids) {
        SqlSession session = SqlSessionUtils.openSession(true);
        int count = session.delete("subsidy.batch",ids);
        session.close();
        return count;
    }
    /**
     * 根据id查询人员对象
     * @param id id主键和personId
     * @return 补贴对象
     */
    @Override
    public Subsidy queryById(int id) {
        SqlSession session = SqlSessionUtils.openSession();
        Subsidy subsidy = session.selectOne("subsidy.queryById",id);
        session.close();
        return subsidy;
    }
    /**
     * 根据条件查询总条数
     * @param params 查询条件
     * @return 总条数
     */
    @Override
    public long queryCount(Map<String, Object> params) {
        SqlSession session = SqlSessionUtils.openSession();
        long count = session.selectOne("subsidy.queryCount",params);
        session.close();
        return count;
    }
    /**
     * 根据条件分页查询
     * @param params 查询条件
     * @return 结果集-用于存储、循环展示每页的信息
     */
    @Override
    public List<Subsidy> queryByPage(Map<String, Object> params) {
        SqlSession session = SqlSessionUtils.openSession();
        List<Subsidy> list = session.selectList("subsidy.queryByPage",params);
        session.close();
        return list;
    }
    /**
     * 查询所有的数据
     * @return 结果集-用于存储、循环展示每页的信息
     */
    @Override
    public List<Subsidy> queryAll() {
        SqlSession session = SqlSessionUtils.openSession();
        List<Subsidy> list = session.selectList("subsidy.queryAll");
        session.close();
        return list;
    }

}
