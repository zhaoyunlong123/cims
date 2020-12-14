package com.javakc.cims.main.controller;

import com.javakc.cims.util.mybatis.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "MainController",
        urlPatterns = "/main.do",
        loadOnStartup = 0
)
public class MainController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SqlSession session = SqlSessionUtils.openSession();
        int count = session.insert("person.queryCountByMain");
        session.close();
        request.setAttribute("count",count);
        request.getRequestDispatcher("/main.jsp").forward(request,response);

    }
}
