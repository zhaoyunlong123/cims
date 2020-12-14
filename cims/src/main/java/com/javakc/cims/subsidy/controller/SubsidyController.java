package com.javakc.cims.subsidy.controller;


import com.javakc.cims.person.entity.Person;
import com.javakc.cims.subsidy.entity.Subsidy;
import com.javakc.cims.subsidy.factory.SubsidyFactory;
import com.javakc.cims.subsidy.service.SubsidyService;
import com.javakc.cims.util.validate.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(
        name = "SubsidyController",
        urlPatterns = "/subsidy.do",
        loadOnStartup = 0
)
public class SubsidyController extends HttpServlet {

    private SubsidyService subsidyService = SubsidyFactory.getSubsidyService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 收集参数
        String type = request.getParameter("type");
        String kc = request.getParameter("kc");
        String thispage = request.getParameter("thisPage");
        if (Validator.isInteger(type) && ("1".equals(type) || "2".equals(type))) {
            if (Validator.isNotEmpty(kc) && "create".equals(kc)) {
                //1. 收集参数  //2. 封装数据
                Subsidy subsidy = new Subsidy();

                String money = request.getParameter("money");
                subsidy.setMoney(new BigDecimal(money));

                subsidy.setType(Integer.valueOf(type));

                Person person = new Person();

                String name = request.getParameter("name");
                person.setName(name);

                String personCard = request.getParameter("card");
                person.setPersonCard(personCard);

                String personId = request.getParameter("personId");
                person.setId(Integer.valueOf(personId));

                subsidy.setPerson(person);

                subsidyService.insert(subsidy);

                response.sendRedirect(request.getContextPath() + "/subsidy.do?type=" + type);
            }
            else if (Validator.isNotEmpty(kc) && "delete".equals(kc)) {
                //获取参数
                String id = request.getParameter("id");
                if (Validator.isInteger(id)) {
                    subsidyService.delete(Integer.valueOf(id));
                }
                response.sendRedirect(request.getContextPath()+"/subsidy.do?type="+type+"&thisPage="+thispage);
            }
            else if (Validator.isNotEmpty(kc) && "load".equals(kc)) {
                //获取参数
                String id = request.getParameter("id");
                if (Validator.isInteger(id)) {
                    Subsidy subsidy = subsidyService.queryById(Integer.valueOf(id));
                    request.setAttribute("subsidy",subsidy);
                    request.getRequestDispatcher("/views/subsidy/update.jsp?thisPage="+thispage).forward(request,response);
                }
                else
                {
                    response.sendRedirect(request.getContextPath() + "/subsidy.do?type="+type+"&thisPage="+thispage);
                }
            }
            else if (Validator.isNotEmpty(kc) && "batch".equals(kc)) {
                //获取参数
                String[] ids = request.getParameterValues("ids");
                subsidyService.delete(ids);
                response.sendRedirect(request.getContextPath()+"/subsidy.do?type="+type+"&thisPage="+thispage);
            }
            else if (Validator.isNotEmpty(kc) && "update".equals(kc)) {
                //获取参数
                Subsidy subsidy = new Subsidy();

                String id = request.getParameter("id");
                if (Validator.isInteger(id)){
                    subsidy.setId(Integer.valueOf(id));
                }
                String money = request.getParameter("money");
                if (Validator.isNotEmpty(money)) {
                    subsidy.setMoney(new BigDecimal(money));
                }
                Person person = new Person();
                String reasons = request.getParameter("reasons");
                person.setReasons(reasons);
                subsidy.setPerson(person);
                subsidyService.update(subsidy);
                response.sendRedirect(request.getContextPath()+"/subsidy.do?type="+type+"&thisPage="+thispage);
            }
            else {
                Map<String, Object> params = new HashMap<>();
                //名字查询
                String name = request.getParameter("name");
                params.put("name", null == name ? "" : name);
                String subsidyCard = request.getParameter("subsidyCard");
                params.put("subsidyCard",null==subsidyCard?"":subsidyCard);
                String month = request.getParameter("month");
                params.put("month",null==month?"":month);


                params.put("type", type);
                //1. 分页相关的参数
                // 当前页
                int thisPage = 1;
                String page = request.getParameter("thisPage");
                if (Validator.isInteger(page)) {
                    thisPage = Integer.valueOf(page);
                }
                params.put("thisPage", thisPage);
                //展示每页的条数
                int pageNumber = 10;
                params.put("pageNumber", pageNumber);
                //分页起始条数
                int startNumber = (thisPage - 1) * pageNumber;
                params.put("startNumber", startNumber);
                //总条数
                long count = subsidyService.queryCount(params);
                params.put("count", count);
                //最大页码
                int maxPage = (int) Math.ceil((count * 1.0) / pageNumber);
                params.put("maxPage", maxPage);
                //最后一页条数
                int lastNumber = 10 - (maxPage * pageNumber - (int) count);
                params.put("lastNumber", lastNumber);
                //最后一页的条数
                int lastPageNumber = 10 - ( maxPage*pageNumber - (int)count );
                params.put("lastPageNumber",lastPageNumber);
                //调用数据库分页查询
                List<Subsidy> list = subsidyService.queryByPage(params);

//            List<Subsidy> list = subsidyService.queryAll();


                //2. 查询数据


                //数据写入请求中
                request.setAttribute("params", params);
                request.setAttribute("list", list);
                request.setAttribute("type", type);
                //3. 返回响应 重定向
                request.getRequestDispatcher("/views/subsidy/list.jsp").forward(request, response);
            }
        }
    }
}
