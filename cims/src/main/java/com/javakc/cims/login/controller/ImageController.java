package com.javakc.cims.login.controller;

import com.javakc.cims.util.image.ImageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ImageController",
        urlPatterns = "/image.do",
        loadOnStartup = 0
)
public class ImageController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 生成验证码
        String verifyCode = ImageUtil.generateVerifyCode(4);
        // 保存验证码
        request.getSession().setAttribute("verifyCode",verifyCode);
        // 生成验证码图片
        ImageUtil.outputImage(90,40,response.getOutputStream(),verifyCode);
    }
}
