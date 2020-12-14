package com.javakc.cims.city.controller;

import com.google.gson.Gson;
import com.javakc.cims.util.mybatis.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "CityController",
        urlPatterns = "/city.do",
        loadOnStartup = 0
)
public class CityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //收集参数
        String pcode = request.getParameter("pcode");
        //查询数据库指定的数据
        SqlSession session = SqlSessionUtils.openSession();
        List<Map<String,String>> list = session.selectList("city.queryCityByPcode",pcode);
        session.close();
        //转JSON对象
        Gson gson = new Gson();
        String json = gson.toJson(list);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
