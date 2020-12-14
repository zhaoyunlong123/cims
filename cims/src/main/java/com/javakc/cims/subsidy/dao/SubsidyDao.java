package com.javakc.cims.subsidy.dao;

import com.javakc.cims.subsidy.entity.Subsidy;

import java.util.List;
import java.util.Map;

/**
 * [离退休补贴管理系统]
 * [退休补贴-补贴数据层接口]
 */
public interface SubsidyDao {

    /**
     * 离退休人员补贴添加
     * @param entity 对象
     * @return 状态-成功返回1，失败返回0
     */
    public int insert(Subsidy entity);

    /**
     * 离退休人员补贴信息修改
     * @param entity 对象
     * @return 状态-成功返回1，失败返回0
     */
    public int update(Subsidy entity);

    /**
     * 离退休人员补贴删除
     * @param id 对象
     * @return 状态-成功返回1，失败返回0
     */
    public int delete(int id);

    /**
     * 离退休人员补贴删除
     * @param ids 对象
     * @return 状态-成功返回1，失败返回0
     */
    public int delete(String... ids);

    /**
     * 根据id查询人员对象
     * @param id id主键
     * @return 补贴对象
     */
    public Subsidy queryById(int id);

    /**
     * 根据条件查询总条数
     * @param params 查询条件
     * @return 总条数
     */
    public long queryCount(Map<String,Object> params);

    /**
     * 根据条件分页查询
     * @param params 查询条件
     * @return 结果集-用于存储、循环展示每页的信息
     */
    public List<Subsidy> queryByPage(Map<String,Object> params);

    /**
     * 查询所有的数据
     * @return 结果集-用于存储、循环展示每页的信息
     */
    @Deprecated
    public List<Subsidy> queryAll();


}
