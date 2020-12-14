package com.javakc.cims.person.service;

import com.javakc.cims.person.entity.Person;

import java.util.List;
import java.util.Map;

/**
 * [干部收入管理系统]
 * [人员管理-人员逻辑层接口]
 */
public interface PersonService {
    /**
     * 人员添加
     * @param entity 对象
     * @return 状态(如果成功返回1，失败返回0)
     */
    public int insert(Person entity);
    /**
     * 人员修改
     * @param entity 对象
     * @return 状态(如果成功返回1，失败返回0)
     */
    public int update(Person entity);
    /**
     * 人员删除
     * @param id 主键
     * @return 状态(如果成功返回1，失败返回0)
     */
    public int delete(int id);
    /**
     * 人员删除
     * @param ids 主键数组
     * @return 状态(如果成功返回1，失败返回0)
     */
    public int delete(String... ids);

    /** ------------------------------ */

    /**
     * 根据人员主键查询人员对象
     * @param id 对象
     * @return 人员对象
     */
    public Person queryById(int id);
    /**
     * 根据条件查询总条数
     * @param params 查询条件
     * @return 总条数
     */
    public long queryByCount(Map<String, Object> params);
    /**
     * 根据条件分页查询
     * @param params 查询条件
     * @return 结果集
     */
    public List<Person> queryByPage(Map<String, Object> params);

    /**
     * 查询所有数据
     * @return 结果集
     */
    @Deprecated  //过时-仅测试用
    public List<Person> queryAll();

    /**
     * 根据身份证号查询唯一结果
     * @param personCard 身份证号
     * @return 结果集
     */
    public Map<String,Object> queryCountByPersonCard(String personCard);

    /**
     * 根据补贴类型查询有补贴项，但当月未发放的人员
     * @param type 补贴类型
     * @return 人员信息
     */
    public List<Map<String, Object>> queryPersonBySubsidy(int type);

}
