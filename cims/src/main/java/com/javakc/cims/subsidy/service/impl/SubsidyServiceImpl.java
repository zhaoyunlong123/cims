package com.javakc.cims.subsidy.service.impl;

import com.javakc.cims.subsidy.dao.SubsidyDao;
import com.javakc.cims.subsidy.entity.Subsidy;
import com.javakc.cims.subsidy.factory.SubsidyFactory;
import com.javakc.cims.subsidy.service.SubsidyService;
import com.javakc.cims.util.validate.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.time.LocalTime.now;
import static jdk.nashorn.internal.objects.NativeDate.getMonth;
import static jdk.nashorn.internal.objects.NativeDate.getYear;


public class SubsidyServiceImpl implements SubsidyService {

    private SubsidyDao subsidyDao = SubsidyFactory.getSubsidyDao();

    /**
     * 离退休人员补贴添加
     * @param subsidy 对象
     * @return 状态-成功返回1，失败返回0
     */
    @Override
    public int insert(Subsidy subsidy) {
        LocalDateTime d = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        String month = d.format(dtf);
        subsidy.setMonth(month);
        return subsidyDao.insert(subsidy);
    }
    /**
     * 离退休人员补贴信息修改
     * @param entity 对象
     * @return 状态-成功返回1，失败返回0
     */
    @Override
    public int update(Subsidy entity) {
        return subsidyDao.update(entity);
    }
    /**
     * 离退休人员补贴删除
     * @param id 对象
     * @return 状态-成功返回1，失败返回0
     */
    @Override
    public int delete(int id) {
        return subsidyDao.delete(id);
    }
    /**
     * 离退休人员补贴删除
     * @param ids 对象
     * @return 状态-成功返回1，失败返回0
     */
    @Override
    public int delete(String... ids) {
        return subsidyDao.delete(ids);
    }
    /**
     * 根据id查询人员对象
     * @param id id主键
     * @return 补贴对象
     */
    @Override
    public Subsidy queryById(int id) {
        return subsidyDao.queryById(id);
    }
    /**
     * 根据条件查询总条数
     * @param params 查询条件
     * @return 总条数
     */
    @Override
    public long queryCount(Map<String, Object> params) {
        return subsidyDao.queryCount(params);
    }
    /**
     * 根据条件分页查询
     * @param params 查询条件
     * @return 结果集-用于存储、循环展示每页的信息
     */
    @Override
    public List<Subsidy> queryByPage(Map<String, Object> params) {
//        if (Validator.isNotEmpty(params.get("month")))
//        {
//            String month1 = (String)params.get("month");
//            String[] s = month1.split("-");
//            String month=s[0]+"年"+s[1]+"月";
//            params.put("month",month);
//        }
//        System.out.println(params.get("month"));
        return subsidyDao.queryByPage(params);
    }
    /**
     * 查询所有的数据
     * @return 结果集-用于存储、循环展示每页的信息
     */
    @Override
    public List<Subsidy> queryAll() {
        return subsidyDao.queryAll();
    }
}
