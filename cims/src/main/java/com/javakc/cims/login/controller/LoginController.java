package com.javakc.cims.login.controller;

import com.javakc.cims.user.entity.User;
import com.javakc.cims.user.factory.UserFactory;
import com.javakc.cims.user.service.UserService;
import com.javakc.cims.util.validate.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
    name = "LoginController",
    urlPatterns = "/login.do",
    loadOnStartup = 0
)
public class LoginController extends HttpServlet {

    private UserService userService = UserFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 判断验证码是否正确
        // 1. 从Session中获取验证码 --> 系统生成
        String s1 = (String)request.getSession().getAttribute("verifyCode");
        // 2. 从request中获取验证码 --> 用户输入码
        String s2 = request.getParameter("ima");
        if (s1.equalsIgnoreCase(s2))
        {
            String userName = request.getParameter("userName");
            String passWord = request.getParameter("passWord");
            if(Validator.isNotEmpty(userName) && Validator.isNotEmpty(passWord))
            {
                User user = userService.queryByLogin(userName, passWord);
                if(Validator.isNotEmpty(user))
                {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                    return;
                }
            }
        }

        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }

}
