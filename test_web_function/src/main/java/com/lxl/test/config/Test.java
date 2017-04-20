package com.lxl.test.config;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Administrator on 2017/4/18.
 */
public class Test  implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {


        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

//        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
    }
}
