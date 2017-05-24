package com.easyweb.project;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Administrator on 2017/5/24.
 */
public class WebApplicationStartup implements WebApplicationInitializer {



    private static final String SERVLET_NAME = "Spring-mvc";

    private static final long MAX_FILE_UPLOAD_SIZE = 1024 * 1024 * 5; // 5 Mb

    private static final int FILE_SIZE_THRESHOLD = 1024 * 1024; // After 1Mb

    private static final long MAX_REQUEST_SIZE = -1L; // No request size limit


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        this.addServlet(servletContext);
    }


    private void addServlet(ServletContext servletContext) {
        // 构建一个application context
        AnnotationConfigWebApplicationContext webContext = createWebContext(SpringMVC.class);
        // 注册spring mvc 的 servlet
        ServletRegistration.Dynamic dynamic = servletContext.addServlet(SERVLET_NAME, new DispatcherServlet(webContext));
        // 添加springMVC 允许访问的Controller后缀
//        dynamic.addMapping("*.html", "*.ajax", "*.css", "*.js", "*.gif", "*.jpg", "*.png");
        // 全部通过请用 “/”
         dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
        dynamic.setMultipartConfig(new MultipartConfigElement(null, MAX_FILE_UPLOAD_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));
    }


    private AnnotationConfigWebApplicationContext createWebContext(Class<?>... annotatedClasses) {
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(annotatedClasses);
        return webContext;
    }


}
