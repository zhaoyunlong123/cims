package com.javakc.cims.person.dao.impl;

import com.javakc.cims.person.dao.PersonDao;
import com.javakc.cims.person.entity.Person;
import com.javakc.cims.util.mybatis.SqlSessionUtils;
import com.javakc.cims.util.validate.Validator;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * [干部收入管理系统]
 * [人员管理-人员数据层实现]
 */
public class PersonDaoImpl implements PersonDao {

    /**
     * 线程安全的单例
     */
    private PersonDaoImpl(){
    }

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会恢复，从而实现了延迟加载
     */
    private static class PersonDaoImplHolder
    {
        /**
         * 静态初始化，由JVM来保证线程安全
         */
        private static PersonDaoImpl instance = new PersonDaoImpl();
    }

    public static PersonDaoImpl getInstance()
    {
        return PersonDaoImplHolder.instance;
    }

    /**
     * 添加数据
     * @param entity 对象
     * @return 0或1
     */
    @Override
    public int insert(Person entity) {
        SqlSession session = SqlSessionUtils.openSession(true);
        int result = session.insert("person.insert",entity);
        session.close();
        return result;
    }
    /**
     * 修改数据
     * @param entity 对象
     * @return 0或1
     */
    @Override
    public int update(Person entity) {
        SqlSession session = SqlSessionUtils.openSession(true);
        int result = session.update("person.update",entity);
        session.close();
        return result;
    }
    /**
     * 删除数据
     * @param id 对象
     * @return 0或1
     */
    @Override
    public int delete(int id) {
        SqlSession session = SqlSessionUtils.openSession(true);
        int result = session.delete("person.delete",id);
        session.close();
        return result;
    }
    /**
     * 删除多条数据
     * @param ids 对象
     * @return 删除条数
     */
    @Override
    public int delete(int... ids) {
        SqlSession session = SqlSessionUtils.openSession(true);
        int result = session.delete("person.batch",ids);
        session.close();
        return result;
    }
    /**
     * 查询id
     * @param id 对象
     * @return 对象
     */
    @Override
    public Person queryById(int id) {
        SqlSession session = SqlSessionUtils.openSession();
        Person person = session.selectOne("person.queryById",id);
        session.close();
        return person;
    }
    /**
     * 调用数据库查询总条数
     * @param params 对象
     * @return long类型的数据
     */
    @Override
    public long queryByCount(Map<String, Object> params) {
        SqlSession session = SqlSessionUtils.openSession();
        Long count = session.selectOne("person.queryByCount",params);
        session.close();
        return count;
    }
    /**
     * 调用数据库分页查询
     * @param params 传入limit的两个参数：分页起始条数和每页展示条数
     * @return 数据层返回的查找集合
     */
    @Override
    public List<Person> queryByPage(Map<String, Object> params) {
        SqlSession session = SqlSessionUtils.openSession();
        List<Person> list = session.selectList("person.queryByPage",params);
        session.close();
        return list;
    }
    /**
     * 查找全部数据
     * @return 数据层返回的查找集合
     */
    @Override
    public List<Person> queryAll() {
        //SQL与java分离
        SqlSession session = SqlSessionUtils.openSession();
        List<Person> list = session.selectList("person.queryAll");
        session.close();
        return list;
    }
    /**
     * 根据身份证号查询唯一结果
     * @param personCard 身份证号
     * @return 唯一结果
     */
    @Override
    public int queryCountByPersonCard(String personCard) {
        SqlSession session = SqlSessionUtils.openSession();
        int count = session.selectOne("person.queryCount",personCard);
        session.close();
        return count;
    }
    /**
     * 根据补贴类型查询有补贴项，但当月未发放的人员
     * @param type 补贴类型
     * @return 人员信息
     */
    @Override
    public List<Map<String, Object>> queryPersonBySubsidy(int type) {
        SqlSession session = SqlSessionUtils.openSession();
        List<Map<String, Object>> list = session.selectList("person.queryPersonBySubsidy",type);
        session.close();
        return list;
    }

}
