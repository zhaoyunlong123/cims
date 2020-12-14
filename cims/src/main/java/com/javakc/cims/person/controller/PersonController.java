package com.javakc.cims.person.controller;

import com.google.gson.Gson;
import com.javakc.cims.person.entity.Person;
import com.javakc.cims.person.factory.PersonFactory;
import com.javakc.cims.person.service.PersonService;
import com.javakc.cims.util.validate.Validator;
import org.apache.ibatis.annotations.Param;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "PersonController",
        urlPatterns = "/person.do",
        loadOnStartup = 0
)
public class PersonController extends HttpServlet {

    /**
     * 获取人员模块逻辑层实现类
     */
    private PersonService personService = PersonFactory.getPersonService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 验证客户端的操作（标示符 用于定义用户行为）
        String kc = request.getParameter("kc");
        String thispage = request.getParameter("thisPage");

        if (Validator.isNotEmpty(kc) && "delete".equals(kc))
        {
            //1.1 收集参数
            String id = request.getParameter("id");
            //1.2 调用数据层
            if (Validator.isInteger(id))
            {
                personService.delete(Integer.valueOf(id));
            }
            //1.3 返回响应
            response.sendRedirect(request.getContextPath()+"/person.do?thisPage="+thispage);
        }
        else if (Validator.isNotEmpty(kc) && "batch".equals(kc))
        {
            //1.1 收集参数
            String[] ids = request.getParameterValues("ids");
            //1.2 调用数据层
            personService.delete(ids);
            //1.3 返回响应
            response.sendRedirect(request.getContextPath()+"/person.do?thisPage="+thispage);
        }
        else if (Validator.isNotEmpty(kc) && "card".equals(kc))
        {
            //1. 收集参数
            String personCard = request.getParameter("personCard");
            if (Validator.isNotEmpty(personCard) && personCard.length() == 18)
            {
                //查询结果
                Map<String,Object> result = personService.queryCountByPersonCard(personCard);
                //文本转json
                Gson gson = new Gson();
                String json = gson.toJson(result);
                //字节流写入
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
                writer.close();
            }

        }
        else if (Validator.isNotEmpty(kc) && "subsidy".equals(kc))
        {
            //1. 收集参数
            String type = request.getParameter("type");
            if (Validator.isInteger(type) && ("1".equals(type) || "2".equals(type)))
            {
                //查询结果
                List<Map<String,Object>> list = personService.queryPersonBySubsidy(Integer.parseInt(type));
                //文本转json
                Gson gson = new Gson();
                String json = gson.toJson(list);
                //字节流写入
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
                writer.close();
            }

        }
        else if (Validator.isNotEmpty(kc) && "create".equals(kc))
        {
            //1.收集参数     //2.封装数据
            Person entity = new Person();
            String name = request.getParameter("name");
            entity.setName(name);

            String personCard = request.getParameter("personCard");
            entity.setPersonCard(personCard);

            String state = request.getParameter("state");
            if (Validator.isInteger(state))
            {
                entity.setState(Integer.valueOf(state));
            }

            String grade = request.getParameter("grade");
            if (Validator.isInteger(grade))
            {
                entity.setGrade(Integer.valueOf(grade));
            }

            String startedDate1 = request.getParameter("startedDate");
            if (Validator.isDate(startedDate1))
            {
                LocalDate startedDate2 = LocalDate.parse(startedDate1);
                ZoneId zone = ZoneId.systemDefault();
                Instant instant = startedDate2.atStartOfDay().atZone(zone).toInstant();
                Date startedDate = Date.from(instant);
                entity.setStartedDate(startedDate);
            }

            String hotting = request.getParameter("hotting");
            if (Validator.isInteger(hotting))
            {
                entity.setHotting(Integer.valueOf(hotting));
            }

            String property = request.getParameter("property");
            if (Validator.isInteger(property))
            {
                entity.setProperty(Integer.valueOf(property));
            }

            String reasons = request.getParameter("reasons");
            entity.setReasons(reasons);

            //3.调用逻辑层
            personService.insert(entity);

            //4.返回响应
            response.sendRedirect(request.getContextPath()+"/person.do?thisPage="+thispage);

        }
        else if (Validator.isNotEmpty(kc) && "load".equals(kc))
        {
            //1. 收集参数
            String id = request.getParameter("id");
            if (Validator.isInteger(id))
            {
               Person entity = personService.queryById(Integer.valueOf(id));
               request.setAttribute("entity",entity);
               request.getRequestDispatcher("/views/person/update.jsp").forward(request,response);
            }
            else
            {
                //2. 返回响应
                response.sendRedirect(request.getContextPath()+"/person.do");
            }
        }
        else if (Validator.isNotEmpty(kc) && "update".equals(kc))
        {
            //1.收集参数     //2.封装数据
            Person entity = new Person();
            String id = request.getParameter("id");
            if (Validator.isInteger(id))
            {
                entity.setId(Integer.valueOf(id));
            }

            String name = request.getParameter("name");
            entity.setName(name);

            String personCard = request.getParameter("personCard");
            entity.setPersonCard(personCard);

            String state = request.getParameter("state");
            if (Validator.isInteger(state))
            {
                entity.setState(Integer.valueOf(state));
            }

            String grade = request.getParameter("grade");
            if (Validator.isInteger(grade))
            {
                entity.setGrade(Integer.valueOf(grade));
            }

            String startedDate1 = request.getParameter("startedDate");
            if (Validator.isDate(startedDate1))
            {
                LocalDate startedDate2 = LocalDate.parse(startedDate1);
                ZoneId zone = ZoneId.systemDefault();
                Instant instant = startedDate2.atStartOfDay().atZone(zone).toInstant();
                Date startedDate = Date.from(instant);
                entity.setStartedDate(startedDate);
            }

            String hotting = request.getParameter("hotting");
            if (Validator.isInteger(hotting))
            {
                entity.setHotting(Integer.valueOf(hotting));
            }

            String property = request.getParameter("property");
            if (Validator.isInteger(property))
            {
                entity.setProperty(Integer.valueOf(property));
            }

            String reasons = request.getParameter("reasons");
            entity.setReasons(reasons);

            //3.调用逻辑层
            personService.update(entity);

            //4.返回响应
            response.sendRedirect(request.getContextPath()+"/person.do");
        }
        else
        {
            Map<String,Object> params = new HashMap<>();
            //条件查询
            //1.1名字查询
            String name = request.getParameter("name");
            params.put("name",null == name ? "" : name);
            //1.2身份证号查询
            String card = request.getParameter("card");
            params.put("card",null == card ? "" : card);
            //1.3状态查询 1--离休  2--退休
            String state = request.getParameter("state");
            //将String转为int类型
            int stateNumber = 0;
            if (Validator.isInteger(state))
            {
                stateNumber = Integer.valueOf(state);
                params.put("state",stateNumber);
            }
            //1.4日期查询 收集客户端参数（String） 起始日期--startDate  结束日期--endDate
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            //获取最终转换结果（Date）
            Date startDate1 = null;
            Date endDate1 = null;
            //日期验证
            if (Validator.isDate(startDate) && Validator.isDate(endDate))
            {
                LocalDate startDate2 = LocalDate.parse(startDate);
                LocalDate endDate2 = LocalDate.parse(endDate);
                if (startDate2.isBefore(endDate2))
                {
                    //日期转换 LocalDate --> Date
                    ZoneId zone = ZoneId.systemDefault();
                    Instant instant1 = startDate2.atStartOfDay().atZone(zone).toInstant();
                    Instant instant2 = endDate2.atStartOfDay().atZone(zone).toInstant();
                    startDate1 = Date.from(instant1);
                    endDate1 = Date.from(instant2);
                    params.put("startDate",startDate1);
                    params.put("endDate",endDate1);
                }
            }
            //1.5供暖、物业补贴
            String hotting = request.getParameter("hotting");
            if (Validator.isInteger(hotting))
            {
                params.put("hotting",1);
            }
            String property = request.getParameter("property");
            if (Validator.isInteger(property))
            {
                params.put("property",1);
            }


            //1.计算分页相关参数
            //当前页
            int thisPage = 1;
            String page = request.getParameter("thisPage");
            if (Validator.isInteger(page))
            {
                thisPage = Integer.valueOf(page);
            }
            //每页展示条数
            int pageNumber = 10;
            params.put("limit",pageNumber);
            //分页起始条数
            int startNumber = (thisPage-1)*pageNumber;
            params.put("start",startNumber);
            //总条数
            long count = personService.queryByCount(params);
            //最大页码
            int maxPage = (int)Math.ceil((count*1.0)/pageNumber);
            //最后一页条数
            int lastPageNumber = 10 - (maxPage * pageNumber - (int)count);

            //调用数据库分页查询
            List<Person> list = personService.queryByPage(params);

            //2. 数据写入请求中
            request.setAttribute("list",list);
            request.setAttribute("thisPage",thisPage);
            request.setAttribute("maxPage",maxPage);
            request.setAttribute("params",params);
            request.setAttribute("count",count);
            request.setAttribute("lastPageNumber",lastPageNumber);
            //3. 跳转到人员展示
            request.getRequestDispatcher("/views/person/list.jsp").forward(request,response);
        }
    }
}
