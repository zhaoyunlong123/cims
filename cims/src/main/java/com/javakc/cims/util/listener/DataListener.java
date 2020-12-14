package com.javakc.cims.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;

@WebListener
public class DataListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //1. 数据字典   查询数据库
        //2. 写入作用域（缓存）
        sce.getServletContext().setAttribute("date",
                Map.of(1,"科级副职",
                        2,"科级正职",
                        3,"处级副职",
                        4,"处级正职",
                        5,"司级副职",
                        6,"司级正职",
                        7,"部级副职",
                        8,"部级正职"
        ));
    }
}
