package com.bayram.managedbeans;

import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.bayram.config.AppConfig;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext applicationContext=new AnnotationConfigWebApplicationContext();
		applicationContext.register(AppConfig.class);
		applicationContext.setServletContext(servletContext);
		servletContext.addListener(new ContextLoaderListener(applicationContext));
		servletContext.addListener(new RequestContextListener());
		
		Dynamic dynamic=servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);

	}

}
