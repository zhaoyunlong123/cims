package com.javakc.cims.person.service.impl;

import com.javakc.cims.person.dao.PersonDao;
import com.javakc.cims.person.entity.Person;
import com.javakc.cims.person.factory.PersonFactory;
import com.javakc.cims.person.service.PersonService;
import com.javakc.cims.util.validate.Validator;

import java.util.List;
import java.util.Map;

/**
 * [干部收入管理系统]
 * [人员管理-人员逻辑层实现]
 * 逻辑层的作用
 */
public class PersonServiceImpl implements PersonService {

    /**
     * 获取人员模块数据层实现类
     */
    private PersonDao personDao = PersonFactory.getPersonDao();

    /**
     * 添加数据
     * @param entity 对象
     * @return 0或1
     */
    @Override
    public int insert(Person entity) {
        return personDao.insert(entity);
    }
    /**
     * 修改数据
     * @param entity 对象
     * @return 0或1
     */
    @Override
    public int update(Person entity) {
        return personDao.update(entity);
    }
    /**
     * 删除数据
     * @param id 对象
     * @return 0或1
     */
    @Override
    public int delete(int id)
    {
        return personDao.delete(id);
    }
    /**
     * 删除多条数据
     * @param ids 对象
     * @return 删除条数
     */
    @Override
    public int delete(String... ids) {
        if (Validator.isNotEmpty(ids))
        {
            //1. 把String转化为int类型
            int[] array = new int[ids.length];
            for (int i = 0; i < ids.length; i++) {
                array[i] = Integer.valueOf(ids[i]);
            }
            personDao.delete(array);

        }
        return 0;
    }
    /**
     * 查询id
     * @param id 对象
     * @return 对象
     */
    @Override
    public Person queryById(int id) {
        return personDao.queryById(id);
    }
    /**
     * 调用数据层查询总条数
     * @param params 对象
     * @return long类型的数据
     */
    @Override
    public long queryByCount(Map<String, Object> params) {
        return personDao.queryByCount(params);
    }
    /**
     * 调用数据层分页查询
     * @param params 传入limit的两个参数：分页起始条数和每页展示条数
     * @return 数据层返回的查找集合
     */
    @Override
    public List<Person> queryByPage(Map<String, Object> params) {
        return personDao.queryByPage(params);
    }
    /**
     * 查找全部数据
     * @return 数据层返回的查找集合
     */
    @Override
    public List<Person> queryAll() {
        return personDao.queryAll();
    }

    @Override
    public Map<String, Object> queryCountByPersonCard(String personCard) {
        int count = personDao.queryCountByPersonCard(personCard);
        return Map.of("success",count==0,"message",count==0?"恭喜添加成功！":"已存在，请重新添加！");
    }
    /**
     * 根据补贴类型查询有补贴项，但当月未发放的人员
     * @param type 补贴类型
     * @return 人员信息
     */
    @Override
    public List<Map<String, Object>> queryPersonBySubsidy(int type) {
        return personDao.queryPersonBySubsidy(type);
    }
}
